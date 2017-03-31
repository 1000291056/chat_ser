package controller;

import entity.Response;
import hibernate.HibernateUtil;
import hibernate.bean.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@RestController
public class PersonInfoController {
    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody(required = true) User user) {
        Response<String> response = new Response();
        if (user == null || !user.verificate()) {
            response.setStatus(-1);
            response.setMessage("请确认信息");
        } else {
            Session session = null;
            Transaction transaction = null;
            try {
                session = HibernateUtil.getSession();
                transaction = session.beginTransaction();
                session.saveOrUpdate("name", user);
                transaction.commit();
                response.setStatus(200);
                response.setMessage("OK");
                response.setData("successful");
            } catch (HibernateException e) {
                response.setStatus(-1);
                response.setMessage("用户已存在");
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return response;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody(required = true) User user) {
        Response<User> response = new Response();
        if (user == null || !user.verificate()) {
            response.setStatus(-1);
            response.setMessage("请确认信息");
        } else {
            Session session = null;
            Transaction transaction = null;
            try {
                session = HibernateUtil.getSession();
                transaction = session.beginTransaction();
                Criteria criteria = session.createCriteria(User.class);
                Criterion criterion = Restrictions.eq("name", user.getName());
                criteria.add(criterion);
                List<User> list = criteria.list();
                if (list == null || list.isEmpty()) {
                    response.setStatus(-1);
                    response.setMessage("用户不存在");
                } else {
                    User userTemp = list.get(0);
                    if (userTemp.getPassword().equals(user.getPassword())) {
                        response.setStatus(200);
                        response.setMessage("OK");
                        response.setData(userTemp);
                    } else {
                        response.setStatus(-1);
                        response.setMessage("密码错误");
                    }

                }

            } catch (HibernateException e) {
                response.setStatus(-1);
                response.setMessage("系统正忙......");
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return response;
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody(required = true) User user) {
        Response<String> response = new Response();
        if (user == null || !user.verificate()) {

        } else {
            Session session = null;
            Transaction transaction = null;
            try {
                session = HibernateUtil.getSession();
                transaction = session.beginTransaction();
                Criteria criteria = session.createCriteria(User.class);
                Criterion criterion = Restrictions.eq("name", user.getName());
                criteria.add(criterion);
                List list = criteria.list();
                if (list == null || list.isEmpty()) {
                    response.setStatus(-1);
                    response.setMessage("用户不存在");
                } else {
                    User userTemp = (User) list.get(0);
                    user.setId(userTemp.getId());
                    session.clear();
                    session.update(user);
                    transaction.commit();
                    response.setStatus(200);
                    response.setMessage("OK");
                    response.setData("successful");
                }

            } catch (HibernateException e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return response;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Response getUserList() {
        Response<List<User>> response = new Response();
        List<User> users = new ArrayList<User>();
        Session session = null;
        Transaction transaction = null;
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List list = criteria.list();
        if (list != null) {
            users.addAll(list);
        }
        response.setStatus(200);
        response.setMessage("ok");
        response.setData(users);
        return response;
    }
}
