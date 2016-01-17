package com.example.tectonik.justpass.adapters;

import java.util.ArrayList;

public class Courses {
    public String courseName;
    public String results;

    public Courses(String courseName, String results){
        this.courseName = courseName;
        this.results = results;
    }

    public static ArrayList<Courses> getCourses() {
        ArrayList<Courses> courses = new ArrayList<Courses>();
        courses.add(new Courses("C# Part I", "75"));
        courses.add(new Courses("C# Part II", "69"));
        courses.add(new Courses("C# OOP", "52"));
        courses.add(new Courses("JavaScript OOP", "42"));
        courses.add(new Courses("SPA JS", "55"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        courses.add(new Courses("High Quality Code", "72"));
        return courses;
    }
}
