package cn.hit.sw.lab1.impl;

import cn.hit.sw.entity.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class GeneratorImplTest {
    private GeneratorImpl generator;
    private MyGraph graph;

    @BeforeEach
    public void setUp() {
        // 创建图实例
        graph = new MyGraph("Example");

        // 构建图中的节点和边
        graph.addNode("apple").setAttribute("ui.label", "Apple");
        graph.addNode("banana").setAttribute("ui.label", "Banana");
        graph.addNode("cherry").setAttribute("ui.label", "Cherry");
        graph.addEdge("apple_banana", "apple", "banana", true).setAttribute("ui.label", "1");
        graph.addEdge("banana_cherry", "banana", "cherry", true).setAttribute("ui.label", "1");

        // 初始化GeneratorImpl
        generator = new GeneratorImpl(graph);
    }

    @Test
    public void testQueryBridgeWordsExists() {
        // 测试存在桥接词的情况
        String result = generator.queryBridgeWords("apple", "cherry");
        System.out.println(result);
        Assertions.assertEquals("The bridge words from apple to cherry are: banana.", result);
    }

    @Test
    public void testQueryBridgeWordsNone() {
        // 测试不存在桥接词的情况
        String result = generator.queryBridgeWords("apple", "apple");
        System.out.println(result);
        Assertions.assertEquals("No bridge words from apple to apple!\n", result);
    }

    @Test
    public void testQueryBridgeWordsNoNode() {
        // 测试至少一个节点不存在的情况
        String result = generator.queryBridgeWords("apple", "date");
        System.out.println(result);
        Assertions.assertEquals("No apple or date in the graph!", result);
    }
}
