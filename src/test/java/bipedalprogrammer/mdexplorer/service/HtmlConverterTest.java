package bipedalprogrammer.mdexplorer.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
public class HtmlConverterTest {
    @Test
    public void boldTextShouldRender() {
        HtmlConverter converter = new HtmlConverter();
        String html = converter.convertToInnerHtml("I am *Spartucus*!");
        assertThat(html, is(equalTo("<p>I am <em>Spartucus</em>!</p>\n")));
    }

    @Test
    public void metadataFilter() {
        HtmlConverter converter = new HtmlConverter();
        try (FileInputStream fis = new FileInputStream("src/test/resources/metadata.md")) {
            String markdown = IOUtils.toString(fis, StandardCharsets.UTF_8);
            String html = converter.convertToInnerHtml(markdown);
            assertThat(html, is(equalTo("<p>This is the document. Some <strong>bold</strong> and some <em>italic</em>.</p>\n")));
            assertThat(converter.getMetadata("title"), notNullValue());
            assertThat(converter.getMetadata("author"), notNullValue());
        } catch (Exception e) {
            fail("Missing test file.");
        }
        assertThat(converter.getMetadata("title"), is(equalTo("Test of metadata handling.")));
    }

}