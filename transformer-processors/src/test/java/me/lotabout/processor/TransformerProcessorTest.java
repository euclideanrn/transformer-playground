package me.lotabout.processor;

import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import me.lotabout.annotation.Transformer;
import org.junit.Test;

import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;


public class TransformerProcessorTest {
    private final static String ResourceDir = TransformerProcessorTest.class.getPackage().getName().replace(".", "/");

    @Test
    public void nothingShouldBeGeneratedIfNoAnnotation() {
        String topic = "noannotation";
        Truth.assert_()
                .about(javaSources())
                .that(getResourceFiles(topic, "NoAnnotationBo.java", "NoAnnotationPojo.java"))
                .processedWith(new TransformerProcessor())
                .compilesWithoutError();
    }

    @Test
    public void allMatchedFieldsShouldBeTransformed() {
        String topic = "allmatch";
        Truth.assert_()
                .about(javaSources())
                .that(getResourceFiles(topic, "AllMatchBo.java", "AllMatchPojo.java"))
                .processedWith(new TransformerProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(getResourceFile(topic, "AllMatchTransformer.java"));
    }

    private List<JavaFileObject> getResourceFiles(String topic, String... files) {
        return Arrays.stream(files)
                .map(f -> getResourceFile(topic, f))
                .collect(Collectors.toList());
    }

    private JavaFileObject getResourceFile(String topic, String file) {
        String directory = ResourceDir + "/" + topic + "/";
        return JavaFileObjects.forResource(directory + file);
    }
}
