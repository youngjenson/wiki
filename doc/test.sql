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
order by one.sort, two.sort