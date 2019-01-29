package model;

public class LoginDAO {

    public static void deleteLoginData(Login login){
        String sqlQuery = "DELETE FROM admin WHERE id ='"+login.getId()+"'";
        System.out.println(sqlQuery);
        DbUtility.DeleteData(sqlQuery);
    }


    // Updating Admin data from Admin table
    public static void updateAdminData(Login login) {
        String sqlQuery = "UPDATE admin SET name='"+login.getName()+"',dob='"+login.getDob()+"'," +
                "phonenumber='"+login.getPhonenumber()+"',address='"+login.getAddress()+"',gender='"+login.getGender()+"' WHERE id='"+login.getId()+"' ";
        DbUtility.UpdateData(sqlQuery);
        System.out.println(sqlQuery);
    }
}
