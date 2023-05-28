package dev.era.interpolation.sub;


import dev.era.interpolation.util.Interpreter;

public class TemplateInterpreter extends Interpreter<Object> {


    @Override
    public TemplateInterpreter start(Object value) {
        return (TemplateInterpreter) super.start(value);
    }

    @Override
    public TemplateInterpreter end(Object value) {
        return (TemplateInterpreter) super.end(value);
    }

    @Override
    public Object interpolate(double elapsedTime) {
        return null;
    }
}
