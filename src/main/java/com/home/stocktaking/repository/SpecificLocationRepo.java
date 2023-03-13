package com.home.stocktaking.repository;

import com.home.stocktaking.dto.SpecificLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий выполняет рекурсивный запрос в БД для получения пользователей с его местаположениями с полным именем
 * Пример полного имени локации:
 * Город->Улица->Отдел
 */
@Repository
public interface SpecificLocationRepo extends CrudRepository<SpecificLocation, String> {
    @Query(value =
            "with recursive temp1 (id, parent_id, name, path) as" +
                    " (select t1.id, t1.parent_id, t1.name, cast (t1.name as varchar (350)) as path" +
                    " from stock.place t1 where t1.parent_id is null" +
                    " union" +
                    " select t2.id, t2.parent_id, t2.name, cast (temp1.path || '->'|| t2.name as varchar(350))" +
                    " from stock.place t2 inner join temp1 on (temp1.id = t2.parent_id))" +
                    " select cast (users.user_id as varchar (50)) as user_id, users.user_name, cast (users.user_place_id as varchar (50)), temp1.path from temp1" +
                    " join (select u.id as user_id, u.login, u.name as user_name, ul.place_id as user_place_id from stock.user as u join stock.user_location ul on u.id = ul.user_id) AS users" +
                    " on users.user_place_id = temp1.id", nativeQuery = true)
    List<Object[]> findAllUsersWithAllLocationPaths();
}
