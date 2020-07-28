package cn.xxc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xixincan
 * 2020-07-27
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {

    private String name;

    private Integer age;

}
