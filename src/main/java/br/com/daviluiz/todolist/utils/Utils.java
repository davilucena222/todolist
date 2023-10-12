package br.com.daviluiz.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
  // copiar propriedades de um objeto para outro
  public static void copyNonNullProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
  }

  public static String[] getNullPropertyNames(Object source) {
    // formas de acessar um objeto no java
    final BeanWrapper src = new BeanWrapperImpl(source);
    System.out.println("BeanWrapper: " + src);

    // capturando todas as propriedades do objeto
    PropertyDescriptor[] pds = src.getPropertyDescriptors();
    System.out.println("PropertyDescriptor: " + pds);

    Set<String> emptyNames = new HashSet<>();

    for (PropertyDescriptor pd: pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      System.out.println("srcValue: " + srcValue);

      if (srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    System.out.println("emptyNames [resultado final]: " + emptyNames);

    String[] result = new String[emptyNames.size()];
    System.out.println("result: " + result);
    
    return emptyNames.toArray(result);
  }
}
