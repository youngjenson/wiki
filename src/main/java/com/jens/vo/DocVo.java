package com.jens.vo;

import com.jens.domain.Doc;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DocVo extends Doc {

    private List<Doc> children;
}
