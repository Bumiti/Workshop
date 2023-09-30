package com.workshop.reposetory;

import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RoleCustomerReposetory {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Roles> getRole(User users){
        StringBuilder sql = new StringBuilder().append("select r.name as name from users join users_role ur on users.id = ur.user_id\n" +
                "join roles r on ur.roles_id = r.id\n");
        sql.append("Where 1=1");
        if(users.getEmail()!=null){
            sql.append(" and email = :email");
        }
        NativeQuery<Roles> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        if(users.getEmail()!=null){
            query.setParameter("email", users.getEmail());
        }
        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Roles.class));
        return  query.list();
    }
}
