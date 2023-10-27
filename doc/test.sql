select one.id,
       one.parent,
       one.name,
       one.sort,
       two.id,
       two.name,
       two.parent,
       two.sort
from category one
    left join category two
on two.parent = one.id
where one.parent = 0
order by one.sort, two.sort;


with recursive temp as (
select * from  doc p where  parent= 0
 union all
select t.* from doc t inner join temp on temp.id = t.parent
)

select *  from temp order by temp.id, temp.sort
