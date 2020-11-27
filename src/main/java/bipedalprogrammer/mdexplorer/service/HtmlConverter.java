package bipedalprogrammer.mdexplorer.service;

import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterBlock;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterVisitor;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlConverter {
    private Parser parser;
    private HtmlRenderer renderer;
    private Map<String, String> metadata = new HashMap<>();

    public  HtmlConverter() {
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(YamlFrontMatterExtension.create()));
        this.parser = Parser.builder(options).build();
        this.renderer = HtmlRenderer.builder().build();
    }

    public String convertToInnerHtml(String markdown) {
        Node document = parser.parse(markdown);
        AbstractYamlFrontMatterVisitor visitor = new AbstractYamlFrontMatterVisitor();
        visitor.visit(document);
        Map<String, List<String>> data = visitor.getData();
        data.keySet().stream().forEach(key -> metadata.put(key, data.get(key).get(0).trim()));
        return renderer.render(document);
    }

    public String getMetadata(String key) {
        return metadata.get(key);
    }
}
