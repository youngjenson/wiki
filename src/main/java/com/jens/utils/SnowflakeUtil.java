package com.jens.utils;

import org.springframework.stereotype.Component;

@Component
public class SnowflakeUtil {
    private static final long EPOCH = 1609459200000L; // 起始时间戳，这里设为2021-01-01 00:00:00
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    private static final long MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    private long workerId = 1L;
    private long dataCenterId = 1L;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeUtil(){}

    public SnowflakeUtil(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID can't be greater than " + MAX_WORKER_ID + " or less than 0");
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("Data Center ID can't be greater than " + MAX_DATA_CENTER_ID + " or less than 0");
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeUtil idGenerator = new SnowflakeUtil(1, 1);

        for (int i = 0; i < 10; i++) {
            long id = idGenerator.nextId();
            System.out.println(id);
        }
    }
}

