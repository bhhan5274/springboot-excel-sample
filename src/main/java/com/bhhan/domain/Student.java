package com.bhhan.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "STUDENTS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    private String name;
    private int age;
    private String address;
    private String hobby;
    private double totalAmount;

    @Builder
    public Student(Long id, String name, int age,
                   String address, String hobby, double totalAmount){
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.hobby = hobby;
        this.totalAmount = totalAmount;
    }
}
