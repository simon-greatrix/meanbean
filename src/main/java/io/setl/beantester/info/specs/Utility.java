package io.setl.beantester.info.specs;

import java.lang.System.Logger.Level;
import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.setl.beantester.info.Specs.BeanConstructor;

class Utility {

  static BeanConstructor findParameters(Class<?> beanClass, Executable executable) {
    return findParametersIfPossible(beanClass, executable).orElseThrow(
        () -> new IllegalArgumentException("No parameters found for method \"" + executable + "\" in " + beanClass)
    );
  }


  static Optional<BeanConstructor> findParametersIfPossible(Class<?> beanClass, Executable executable) {
    boolean namesAreKnown = true;

    for (Parameter parameter : executable.getParameters()) {
      if (!parameter.isNamePresent()) {
        System.getLogger("io.setl.beantester").log(
            Level.WARNING,
            "Parameter name not present in \"" + executable + "\" for \"" + beanClass + "\". Remember to compile with \"-parameters\" enabled."
        );
        namesAreKnown = false;
        break;
      }
    }

    if (namesAreKnown) {
      // found it
      List<String> names = new ArrayList<>();
      List<Class<?>> types = new ArrayList<>();

      for (Parameter parameter : executable.getParameters()) {
        names.add(parameter.getName());
        types.add(parameter.getType());
      }

      return Optional.of(new BeanConstructorImpl(names, types));
    }

    return Optional.empty();
  }


  static Optional<BeanConstructorImpl> isNameTypeList(Object[] args) {
    if (args.length % 2 != 0) {
      return Optional.empty();
    }
    ArrayList<String> names = new ArrayList<>(args.length / 2);
    ArrayList<Class<?>> types = new ArrayList<>(args.length / 2);

    for (int i = 0; i < args.length; i += 2) {
      if (!(args[i] instanceof String) || !(args[i + 1] instanceof Class<?>)) {
        return Optional.empty();
      }
      names.add((String) args[i]);
      types.add((Class<?>) args[i + 1]);
    }

    return Optional.of(new BeanConstructorImpl(names, types));
  }


  static Optional<List<Class<?>>> isTypeList(Object[] args) {
    ArrayList<Class<?>> types = new ArrayList<>(args.length);
    for (Object arg : args) {
      if (!(arg instanceof Class<?>)) {
        return Optional.empty();
      }
      types.add((Class<?>) arg);
    }
    return Optional.of(types);
  }

}