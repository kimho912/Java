package person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Person {
    public Person (String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public Person(BufferedReader in) throws IOException {
        this.name = in.readLine();
        this.phone = in.readLine();
    }
    public void save(BufferedWriter out) throws IOException {
        out.write(name + '\n');
        out.write(phone + '\n');
    }
    public String name() {return name;}
    public String phone() {return phone;}
    
    @Override
    public boolean equals(Object rhs) {
        if(this == rhs) return true;
        if(rhs == null) return false;
        if(this.getClass() != rhs.getClass()) return false;

        Person person = (Person) rhs;
        return phone.equals(person.phone);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (phone == null ? 0 : phone.hashCode());
        return hash;
    }
    @Override
    public String toString() {
        return name;
    }
    
    
    protected String name;
    protected String phone;
}
