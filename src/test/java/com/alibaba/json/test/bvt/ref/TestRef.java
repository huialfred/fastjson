package com.alibaba.json.test.bvt.ref;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TestRef extends TestCase {

    public void test_ref() throws Exception {
        Department tech = new Department(1, "技术部");
        tech.setRoot(tech);

        {
            Department pt = new Department(2, "平台技术部");
            pt.setParent(tech);
            pt.setRoot(tech);
            tech.getChildren().add(pt);
            {
                Department sysbase = new Department(3, "系统基础");
                sysbase.setParent(pt);
                sysbase.setRoot(tech);
                pt.getChildren().add(sysbase);
            }
        }
        {
            Department cn = new Department(4, "中文站技术部");
            cn.setParent(tech);
            cn.setRoot(tech);
            tech.getChildren().add(cn);
        }

        {
            String text = JSON.toJSONString(tech, SerializerFeature.PrettyFormat);

            System.out.println(text);
            
            Department dept = JSON.parseObject(text, Department.class);
            
            System.out.println(JSON.toJSONString(dept, SerializerFeature.PrettyFormat));
        }
    }

    private static class Department {

        private int                    id;
        private String                 name;

        private Department             parent;
        private Department             root;

        private Collection<Department> children = new ArrayList<Department>();

        public Department(){

        }

        public Department getRoot() {
            return root;
        }

        public void setRoot(Department root) {
            this.root = root;
        }

        public Department(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Department getParent() {
            return parent;
        }

        public void setParent(Department parent) {
            this.parent = parent;
        }

        public Collection<Department> getChildren() {
            return children;
        }

        public void setChildren(Collection<Department> children) {
            this.children = children;
        }

    }
}
