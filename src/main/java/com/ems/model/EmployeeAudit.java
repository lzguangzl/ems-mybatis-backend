package com.ems.model;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long emp_id;
    private String first_name;
    private String last_name;
    private String changes;

    public EmployeeAudit() {
    }

    public EmployeeAudit(long emp_id, String first_name, String last_name, String changes) {
        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.changes = changes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeAudit)) return false;
        EmployeeAudit that = (EmployeeAudit) o;
        return id == that.id &&
                emp_id == that.emp_id &&
                Objects.equals(first_name, that.first_name) &&
                Objects.equals(last_name, that.last_name) &&
                Objects.equals(changes, that.changes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emp_id, first_name, last_name, changes);
    }
}
