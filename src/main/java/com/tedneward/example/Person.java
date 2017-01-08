package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int count;

  public Person() {
    this("", 0, 0.0d);
  }

  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }

  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

//  public void setSsn(String ssn) {
//    this.ssn = ssn;
//  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;

    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public int getCount() {
    return count;
  }

  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public String getSSN() {
    return ssn;
  }

  public boolean isPropertyChangeFired() {
    return propertyChangeFired;
  }

  public void setPropertyChangeFired(boolean propertyChangeFired) {
    this.propertyChangeFired = propertyChangeFired;
  }

  public PropertyChangeSupport getPcs() {
    return pcs;
  }


  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }

  public String becomeJudge() {
    return "The Honorable " + name;
  }

  public int timeWarp() {
    return age + 10;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return (this.name.equals(p.name) && this.age == p.age);
    }
    return false;
  }

  @Override
  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  @Override
  public int compareTo(Person other) {
    return  (int) (other.getSalary() - this.getSalary());
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> list = new ArrayList<Person>();
    Person ted = new Person("Ted", 41, 250000);
    Person charlotte = new Person("Charlotte", 43, 150000);
    Person michael = new Person("Michael", 22, 10000);
    Person matthew = new Person("Matthew", 15, 0);
    list.add(ted);
    list.add(charlotte);
    list.add(michael);
    list.add(matthew);
    return list;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
  }
}
