package com.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-14 09:52
 **/

public class PartyUtils {

    public static void main(String[] args) throws  Exception {
        PartyUtils utils=new PartyUtils();
        Customer original =utils.new Customer();
        SimpleDateFormat sdf =new SimpleDateFormat("YYYY-MM-DD");
        original.setBirthdate(sdf.parse("1956-12-07"));
        original.setCertiCode("372802195612070312");
        original.setCertiType(1);
        original.setFirstName("尹德青 ");
        original.setGender("M");

        CustomerVO customerVO=utils.new CustomerVO();
        customerVO.setBirthdate(sdf.parse("1956-12-07"));
        customerVO.setCertiCode("372802195612070312");
        customerVO.setCertiType(1);
        customerVO.setFirstName("尹德青");
        customerVO.setGender("M");

        if (utils.equals(original.getFirstName(), customerVO.getFirstName())
                && utils.equals(original.getBirthdate(), customerVO.getBirthdate())
                && utils.equals(original.getCertiType(), customerVO.getCertiType())
                && utils.equals(original.getCertiCode(), customerVO.getCertiCode())
                && utils.equals(original.getGender(), customerVO.getGender())) {
            System.out.println("true");
        }else{
            System.out.println(false);

        }
    }


    public  boolean equals(Object obj0, Object obj1) {

        if (obj0 instanceof String && "".equals(obj0)) {
            obj0 = null;
        }

        if (obj1 instanceof String && "".equals(obj1)) {
            obj1 = null;
        }

        if (obj0 == null && obj1 == null) {
            return true;
        }

        if (obj0 == null) {
            return false;
        }

        if (obj1 == null) {
            return false;
        }

        if (obj0 instanceof BigDecimal && obj1 instanceof BigDecimal) {
            BigDecimal db0 = (BigDecimal) obj0;
            BigDecimal db1 = (BigDecimal) obj1;
            if (db0.compareTo(db1) == 0) {
                return true;
            } else {
                return false;
            }
        }

        if (obj0.equals(obj1)) {
            return true;
        }

        return false;
    }

    class Customer{

        private String gender;

        private Date birthdate;

        private Integer certiType;

        private String certiCode;

        private String firstName;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }

        public Integer getCertiType() {
            return certiType;
        }

        public void setCertiType(Integer certiType) {
            this.certiType = certiType;
        }

        public String getCertiCode() {
            return certiCode;
        }

        public void setCertiCode(String certiCode) {
            this.certiCode = certiCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }



    class CustomerVO {
        private String gender;

        private Date birthdate;

        private Integer certiType;

        private String certiCode;

        private String firstName;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }

        public Integer getCertiType() {
            return certiType;
        }

        public void setCertiType(Integer certiType) {
            this.certiType = certiType;
        }

        public String getCertiCode() {
            return certiCode;
        }

        public void setCertiCode(String certiCode) {
            this.certiCode = certiCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }
}
