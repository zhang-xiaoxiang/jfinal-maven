# user是模块化管理的一种机制
#namespace("user")
#sql("findGirl")
  select * from user where user_sex=?;
#end

#sql("findcqboy")
select * from user where user_site = #para(0) and user_sex = #para(1)  ;
#end

#end