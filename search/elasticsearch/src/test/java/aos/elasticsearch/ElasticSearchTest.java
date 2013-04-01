package aos.elasticsearch;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacet;
import org.elasticsearch.search.facet.filter.FilterFacet;
import org.elasticsearch.search.facet.geodistance.GeoDistanceFacet;
import org.elasticsearch.search.facet.histogram.HistogramFacet;
import org.elasticsearch.search.facet.histogram.HistogramFacetBuilder;
import org.elasticsearch.search.facet.query.QueryFacet;
import org.elasticsearch.search.facet.range.RangeFacet;
import org.elasticsearch.search.facet.statistical.StatisticalFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;
import org.elasticsearch.search.facet.termsstats.TermsStatsFacet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchTest.class);

    private static final int NUMBER_OF_ACTIONS = 100;
    private static final int NUMBER_OF_NODES = 4;
    private static final int NUMBER_OF_INDICES = 600;
    private static final int BATCH = 300;

    private static Client client;

    @BeforeClass
    public static void setUp() {

        final Settings nodeSettings = ImmutableSettings.settingsBuilder().put("index.number_of_shards", 2).build();

        Node[] nodes = new Node[NUMBER_OF_NODES];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = NodeBuilder.nodeBuilder().settings(nodeSettings).node();
        }
        client = client(nodes);

        Settings indexSettings = ImmutableSettings.settingsBuilder() //
                .put("cluster.name", "EmbeddedESCluster") //
                .put("client.transport.sniff", true) //
                .put("client.transport.ignore_cluster_name", true) //
                .put("org.elasticsearch.client.transport", true) //
                .build();
        // client = client(indexSettings, "localhost", 9300);

    }

    @AfterClass
    public static void tearDown() {
        client.close();
    }

    @Test
    public void test() throws IOException {
        testIndex();
        testGet();
        testBulkRequests();
        testDelete();
        testPercolate();
        testSearch();
        testMultiSearch();
        testQueryDslFilters();
        testQueryDslQueries();
        testFacets();
    }

    private void testIndex() throws IOException {

        String jsonString = "{" + //
                "\"user\":\"kimchy\"," + //
                "\"postDate\":\"2013-01-30\"," + //
                "\"message\":\"trying out Elastic Search\"," + "}";

        IndexResponse response = client.prepareIndex("twitter", "tweet") //
                .setSource(jsonString) //
                .execute() //
                .actionGet();

        // Index name
        String index = response.getIndex();
        // Type name
        String type = response.getType();
        // Document ID (generated or not)
        String id = response.getId();
        // Version (if it's the first time you index this document, you will
        // get: 1)
        long version = response.getVersion();

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elastic Search");

        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        String json = mapper.writeValueAsString(jsonMap);
        LOGGER.info(json);

        XContentBuilder builder = jsonBuilder() //
                .startObject() //
                .field("user", "kimchy") //
                .field("postDate", new Date()) //
                .field("message", "trying out Elastic Search") //
                .endObject();

        String json2 = builder.string();

        IndexResponse response2 = client.prepareIndex("twitter", "tweet", "1") //
                .setSource(jsonBuilder() //
                        .startObject() //
                        .field("user", "kimchy") //
                        .field("postDate", new Date()) //
                        .field("message", "trying out Elastic Search") //
                        .endObject() //
                ) //
                .execute() //
                .actionGet();

    }

    private void testGet() {
        GetResponse response = client.prepareGet("twitter", "tweet", "1") //
                .execute() //
                .actionGet();
        GetResponse response2 = client.prepareGet("twitter", "tweet", "1") //
                .setOperationThreaded(false) //
                .execute() //
                .actionGet();
    }

    private void testDelete() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1") //
                .execute() //
                .actionGet();
        DeleteResponse response2 = client.prepareDelete("twitter", "tweet", "1") //
                .setOperationThreaded(false) //
                .execute() //
                .actionGet();
    }

    private void testSearch() {

        SearchResponse response = client.prepareSearch("index1", "index2") //
                .setTypes("type1", "type2") //
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH) //
                .setQuery(QueryBuilders.termQuery("multi", "test")) // Query
                .setFilter(FilterBuilders.rangeFilter("age").from(12).to(18)) // Filter
                .setFrom(0).setSize(60).setExplain(true) //
                .execute() //
                .actionGet();

        // MatchAll on the whole cluster with all default options
        SearchResponse response2 = client.prepareSearch().execute().actionGet();

        QueryBuilder qb = termQuery("multi", "test");

        SearchResponse scrollResp = client.prepareSearch("test") //
                .setSearchType(SearchType.SCAN) //
                .setScroll(new TimeValue(60000)) //
                .setQuery(qb) //
                .setSize(100) //
                .execute() //
                .actionGet();
        // 100 hits per shard will be returned for each scroll
        // Scroll until no hits are returned
        while (true) {
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000))
                    .execute().actionGet();
            for (SearchHit hit : scrollResp.getHits()) {
                // Handle the hit...
            }
            // Break condition: No hits are returned
            if (scrollResp.getHits().hits().length == 0) {
                break;
            }
        }

    }

    private void testMultiSearch() {

        SearchRequestBuilder srb1 = client.prepareSearch() //
                .setQuery(QueryBuilders.queryString("elasticsearch")) //
                .setSize(1);

        SearchRequestBuilder srb2 = client.prepareSearch() //
                .setQuery(QueryBuilders.matchQuery("name", "kimchy")) //
                .setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch() //
                .add(srb1) //
                .add(srb2) //
                .execute() //
                .actionGet();

        // You will get all individual responses from
        // MultiSearchResponse#responses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().totalHits();
        }

    }

    private void testBulkRequests() throws IOException {

        for (int i = 0; i < NUMBER_OF_ACTIONS; i++) {
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (int j = 0; i < BATCH; i++) {
                bulkRequest.add(Requests.indexRequest("test" + ThreadLocalRandom.current().nextInt(NUMBER_OF_INDICES))
                        .type("type").source("field", "value"));
            }
            bulkRequest.execute().actionGet();
        }

        BulkRequestBuilder bulkRequest = client.prepareBulk();

        // either use client#prepare, or use Requests# to directly build
        // index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1") //
                .setSource(jsonBuilder() //
                        .startObject() //
                        .field("user", "kimchy") //
                        .field("postDate", new Date()) //
                        .field("message", "trying out Elastic Search") //
                        .endObject() //
                ));

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2") //
                .setSource(jsonBuilder() //
                        .startObject() //
                        .field("user", "kimchy") //
                        .field("postDate", new Date()) //
                        .field("message", "another post") //
                        .endObject() //
                ));

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }

    }

    private void testQueryDslFilters() {

        FilterBuilders.andFilter(FilterBuilders.rangeFilter("postDate").from("2010-03-01").to("2010-04-01"),
                FilterBuilders.prefixFilter("name.second", "ba"));

        FilterBuilders.boolFilter().must(FilterBuilders.termFilter("tag", "wow"))
                .mustNot(FilterBuilders.rangeFilter("age").from("10").to("20"))
                .should(FilterBuilders.termFilter("tag", "sometag"))
                .should(FilterBuilders.termFilter("tag", "sometagtag"));

        FilterBuilders.existsFilter("user");

        FilterBuilders.idsFilter("my_type", "type2").addIds("1", "4", "100");

        // Type is optional
        FilterBuilders.idsFilter().addIds("1", "4", "100");

        FilterBuilders.limitFilter(100);

        FilterBuilders.typeFilter("my_type");

        FilterBuilders.geoBoundingBoxFilter("pin.location").topLeft(40.73, -74.1).bottomRight(40.717, -73.99);

        FilterBuilders.geoDistanceFilter("pin.location").point(40, -70).distance(200, DistanceUnit.KILOMETERS)
                .optimizeBbox("memory") // Can be also "indexed" or "none"
                .geoDistance(GeoDistance.ARC); // Or GeoDistance.PLANE

        FilterBuilders.geoDistanceRangeFilter("pin.location").point(40, -70).from("200km").to("400km")
                .includeLower(true).includeUpper(false).optimizeBbox("memory") // Can
                                                                               // be
                                                                               // also
                                                                               // "indexed"
                                                                               // or
                                                                               // "none"
                .geoDistance(GeoDistance.ARC); // Or GeoDistance.PLANE

        FilterBuilders.geoPolygonFilter("pin.location").addPoint(40, -70).addPoint(30, -80).addPoint(20, -90);

        // Shape within another
        // FilterBuilder filter = FilterBuilders.geoShapeFilter("location",
        // new RectangleImpl(0, 10, 0, 10,
        // SpatialContext.GEO)).relation(ShapeRelation.WITHIN);

        // Intersect shapes
        // filter = FilterBuilders.geoShapeFilter("location", new PointImpl(0,
        // 0, SpatialContext.GEO)).relation(
        // ShapeRelation.INTERSECTS);

        // Using pre-indexed shapes
        // filter = FilterBuilders.geoShapeFilter("location", "New Zealand",
        // "countries").relation(ShapeRelation.DISJOINT);

        // Has Child
        FilterBuilders.hasChildFilter("blog_tag", QueryBuilders.termQuery("tag", "something"));

        // Has Parent
        FilterBuilders.hasParentFilter("blog", QueryBuilders.termQuery("tag", "something"));

        FilterBuilders.matchAllFilter();

        FilterBuilders.missingFilter("user").existence(true).nullValue(true);

        FilterBuilders.notFilter(FilterBuilders.rangeFilter("price").from("1").to("2"));

        FilterBuilders.numericRangeFilter("age").from(10).to(20).includeLower(true).includeUpper(false);

        FilterBuilders.orFilter(FilterBuilders.termFilter("name.second", "banon"),
                FilterBuilders.termFilter("name.nick", "kimchy"));

        FilterBuilders.prefixFilter("user", "ki");

        FilterBuilders.queryFilter(QueryBuilders.queryString("this AND that OR thus"));

        FilterBuilders.rangeFilter("age").from("10").to("20").includeLower(true).includeUpper(false);

        // A simplified form using gte, gt, lt or lte
        FilterBuilders.rangeFilter("age").gte("10").lt("20");

        FilterBuilder filter2 = FilterBuilders.scriptFilter("doc['age'].value > param1").addParam("param1", 10);

        FilterBuilders.termFilter("user", "kimchy");

        FilterBuilders.termsFilter("user", "kimchy", "elasticsearch").execution("plain"); // Optional,
                                                                                          // can
                                                                                          // be
                                                                                          // also
                                                                                          // "bool",
                                                                                          // "and"
                                                                                          // or
                                                                                          // "or"
                                                                                          // or
                                                                                          // "bool_nocache",
                                                                                          // "and_nocache"
                                                                                          // or
                                                                                          // "or_nocache"

        FilterBuilders.nestedFilter(
                "obj1",
                QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("obj1.name", "blue"))
                        .must(QueryBuilders.rangeQuery("obj1.count").gt(5)));

        FilterBuilder filter = FilterBuilders.andFilter(
                FilterBuilders.rangeFilter("postDate").from("2010-03-01").to("2010-04-01"),
                FilterBuilders.prefixFilter("name.second", "ba")).cache(true);
    }

    private void testQueryDslQueries() {

        QueryBuilder qb = QueryBuilders.matchQuery("name", "kimchy elasticsearch");

        QueryBuilder qb2 = QueryBuilders.multiMatchQuery("kimchy elasticsearch", // Text
                                                                                 // you
                                                                                 // are
                                                                                 // looking
                                                                                 // for
                "user", "message" // Fields you query on
        );

        QueryBuilder qb3 = QueryBuilders.boolQuery().must(termQuery("content", "test1"))
                .must(termQuery("content", "test4")).mustNot(termQuery("content", "test2"))
                .should(termQuery("content", "test3"));

        QueryBuilders.boostingQuery().positive(QueryBuilders.termQuery("name", "kimchy"))
                .negative(QueryBuilders.termQuery("name", "dadoonet")).negativeBoost(0.2f);

        QueryBuilders.idsQuery().ids("1", "2");

        QueryBuilders.customScoreQuery(QueryBuilders.matchAllQuery()) // Your
                                                                      // query
                                                                      // here
                .script("_score * doc['price'].value"); // Your script here

        // If the script have parameters, use the same script and provide
        // parameters to it.
        QueryBuilders.customScoreQuery(QueryBuilders.matchAllQuery())
                .script("_score * doc['price'].value / pow(param1, param2)").param("param1", 2).param("param2", 3.1);

        QueryBuilders.customBoostFactorQuery(QueryBuilders.matchAllQuery()) // Your
                                                                            // query
                .boostFactor(3.1f);

        // Using with Filters
        QueryBuilders.constantScoreQuery(FilterBuilders.termFilter("name", "kimchy")).boost(2.0f);

        // With Queries
        QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("name", "kimchy")).boost(2.0f);

        QueryBuilders.disMaxQuery().add(QueryBuilders.termQuery("name", "kimchy")) // Your
                                                                                   // queries
                .add(QueryBuilders.termQuery("name", "elasticsearch")) // Your
                                                                       // queries
                .boost(1.2f).tieBreaker(0.7f);

        QueryBuilders.fieldQuery("name", "+kimchy -dadoonet");

        // Note that you can write the same query using queryString query.
        QueryBuilders.queryString("+kimchy -dadoonet").field("name");

        // flt Query
        QueryBuilders.fuzzyLikeThisQuery("name.first", "name.last") // Fields
                .likeText("text like this one") // Text
                .maxQueryTerms(12); // Max num of Terms
                                    // in generated queries

        // flt_field Query
        QueryBuilders.fuzzyLikeThisFieldQuery("name.first") // Only on single
                                                            // field
                .likeText("text like this one").maxQueryTerms(12);

        QueryBuilder qb4 = QueryBuilders.fuzzyQuery("name", "kimzhy");

        // Has Child
        QueryBuilders.hasChildQuery("blog_tag", QueryBuilders.termQuery("tag", "something"));

        // Has Parent
        QueryBuilders.hasParentQuery("blog", QueryBuilders.termQuery("tag", "something"));

        QueryBuilder qb5 = QueryBuilders.matchAllQuery();

        // mlt Query
        QueryBuilders.moreLikeThisQuery("name.first", "name.last") // Fields
                .likeText("text like this one") // Text
                .minTermFreq(1) // Ignore Threshold
                .maxQueryTerms(12); // Max num of Terms
                                    // in generated queries

        // mlt_field Query
        QueryBuilders.moreLikeThisFieldQuery("name.first") // Only on single
                                                           // field
                .likeText("text like this one").minTermFreq(1).maxQueryTerms(12);

        QueryBuilders.prefixQuery("brand", "heine");

        QueryBuilder qb6 = QueryBuilders.queryString("+kimchy -elasticsearch");

        QueryBuilder qb7 = QueryBuilders.rangeQuery("price").from(5).to(10).includeLower(true).includeUpper(false);

        // Span First
        QueryBuilders.spanFirstQuery(QueryBuilders.spanTermQuery("user", "kimchy"), // Query
                3 // Max End position
                );

        // Span Near
        QueryBuilders.spanNearQuery().clause(QueryBuilders.spanTermQuery("field", "value1"))
                // Span Term Queries
                .clause(QueryBuilders.spanTermQuery("field", "value2"))
                .clause(QueryBuilders.spanTermQuery("field", "value3")).slop(12) // Slop
                                                                                 // factor
                .inOrder(false).collectPayloads(false);

        // Span Not
        QueryBuilders.spanNotQuery().include(QueryBuilders.spanTermQuery("field", "value1"))
                .exclude(QueryBuilders.spanTermQuery("field", "value2"));

        // Span Or
        QueryBuilders.spanOrQuery().clause(QueryBuilders.spanTermQuery("field", "value1"))
                .clause(QueryBuilders.spanTermQuery("field", "value2"))
                .clause(QueryBuilders.spanTermQuery("field", "value3"));

        // Span Term
        QueryBuilders.spanTermQuery("user", "kimchy");

        QueryBuilder qb8 = QueryBuilders.termQuery("name", "kimchy");

        QueryBuilders.termsQuery("tags", // field
                "blue", "pill") // values
                .minimumMatch(1); // How many terms must match

        QueryBuilders.topChildrenQuery("blog_tag", // field
                QueryBuilders.termQuery("tag", "something") // Query
                ).score("max") // max, sum or avg
                .factor(5).incrementalFactor(2);

        QueryBuilders.wildcardQuery("user", "k?mc*");

        QueryBuilders.nestedQuery(
                "obj1", // Path
                QueryBuilders.boolQuery()
                        // Your query
                        .must(QueryBuilders.matchQuery("obj1.name", "blue"))
                        .must(QueryBuilders.rangeQuery("obj1.count").gt(5))).scoreMode("avg"); // max,
                                                                                               // total,
                                                                                               // avg
                                                                                               // or
                                                                                               // none

        QueryBuilders
                .customFiltersScoreQuery(QueryBuilders.matchAllQuery())
                // Query
                // Filters with their boost factors
                .add(FilterBuilders.rangeFilter("age").from(0).to(10), 3)
                .add(FilterBuilders.rangeFilter("age").from(10).to(20), 2).scoreMode("first"); // first,
                                                                                               // min,
                                                                                               // max,
                                                                                               // total,
                                                                                               // avg
                                                                                               // or
                                                                                               // multiply

        // Using another query when no match for the main one
        QueryBuilders.indicesQuery(QueryBuilders.termQuery("tag", "wow"), "index1", "index2").noMatchQuery(
                QueryBuilders.termQuery("tag", "kow"));

        // Using all (match all) or none (match no documents)
        QueryBuilders.indicesQuery(QueryBuilders.termQuery("tag", "wow"), "index1", "index2").noMatchQuery("all"); // all
                                                                                                                   // or
                                                                                                                   // none

        // Shape within another
        // QueryBuilders.geoShapeQuery("location", new RectangleImpl(0, 10, 0,
        // 10, SpatialContext.GEO)).relation(
        // ShapeRelation.WITHIN);

        // Intersect shapes
        // QueryBuilders.geoShapeQuery("location", new PointImpl(0, 0,
        // SpatialContext.GEO)).relation(
        // ShapeRelation.INTERSECTS);

        // Using pre-indexed shapes
        // QueryBuilders.geoShapeQuery("location", "New Zealand",
        // "countries").relation(ShapeRelation.DISJOINT);

    }

    private void testFacets() {

        SearchResponse sr = client.prepareSearch() //
                .setQuery(QueryBuilders.matchAllQuery()) //
                .addFacet(FacetBuilders.termsFacet("f1").field("field")) //
                .addFacet(FacetBuilders.dateHistogramFacet("f2") //
                        .field("birth") //
                        .interval("year")) //
                .execute() //
                .actionGet();

        // Get your facet results
        TermsFacet f1 = (TermsFacet) sr.getFacets().facetsAsMap().get("f1");
        DateHistogramFacet f2 = (DateHistogramFacet) sr.getFacets().facetsAsMap().get("f2");

        SearchResponse sr2 = client.prepareSearch().setQuery("your query").addFacet(null).execute().actionGet();

        FacetBuilders.termsFacet("f").field("brand").size(10);

        // sr is here your SearchResponse object
        TermsFacet f = (TermsFacet) sr.getFacets().facetsAsMap().get("f");

        f.getTotalCount(); // Total terms doc count
        f.getOtherCount(); // Not shown terms doc count
        f.getMissingCount(); // Without term doc count

        // For each entry
        for (TermsFacet.Entry entry : f) {
            entry.getTerm(); // Term
            entry.getCount(); // Doc count
        }

        FacetBuilders.rangeFacet("f").field("price") // Field to compute on
                .addUnboundedFrom(3) // from -infinity to 3 (excluded)
                .addRange(3, 6) // from 3 to 6 (excluded)
                .addUnboundedTo(6); // from 6 to +infinity

        // sr is here your SearchResponse object
        RangeFacet f3 = (RangeFacet) sr.getFacets().facetsAsMap().get("f");

        // For each entry
        for (RangeFacet.Entry entry : f3) {
            entry.getFrom(); // Range from requested
            entry.getTo(); // Range to requested
            entry.getCount(); // Doc count
            entry.getMin(); // Min value
            entry.getMax(); // Max value
            entry.getMean(); // Mean
            entry.getTotal(); // Sum of values
        }

        HistogramFacetBuilder facet = FacetBuilders.histogramFacet("f").field("price").interval(1);

        // sr is here your SearchResponse object
        HistogramFacet f4 = (HistogramFacet) sr.getFacets().facetsAsMap().get("f");

        // For each entry
        for (HistogramFacet.Entry entry : f4) {
            entry.getKey(); // Key (X-Axis)
            entry.getCount(); // Doc count (Y-Axis)
        }

        FacetBuilders.dateHistogramFacet("f").field("date") // Your date field
                .interval("year"); // You can also use "quarter", "month",
                                   // "week", "day",
                                   // "hour" and "minute" or notation like
                                   // "1.5h" or "2w"

        // sr is here your SearchResponse object
        DateHistogramFacet f5 = (DateHistogramFacet) sr.getFacets().facetsAsMap().get("f");

        // For each entry
        for (DateHistogramFacet.Entry entry : f5) {
            entry.getTime(); // Date in ms since epoch (X-Axis)
            entry.getCount(); // Doc count (Y-Axis)
        }

        FacetBuilders.filterFacet("f", FilterBuilders.termFilter("brand", "heineken")); // Your
                                                                                        // Filter
                                                                                        // here

        // sr is here your SearchResponse object
        FilterFacet f6 = (FilterFacet) sr.getFacets().facetsAsMap().get("f");

        f6.getCount(); // Number of docs that matched

        FacetBuilders.queryFacet("f", QueryBuilders.matchQuery("brand", "heineken"));

        // sr is here your SearchResponse object
        QueryFacet f7 = (QueryFacet) sr.getFacets().facetsAsMap().get("f");

        f7.getCount(); // Number of docs that matched

        FacetBuilders.statisticalFacet("f").field("price");

        // sr is here your SearchResponse object
        StatisticalFacet f8 = (StatisticalFacet) sr.getFacets().facetsAsMap().get("f");

        f8.getCount(); // Doc count
        f8.getMin(); // Min value
        f8.getMax(); // Max value
        f8.getMean(); // Mean
        f8.getTotal(); // Sum of values
        f8.getStdDeviation(); // Standard Deviation
        f8.getSumOfSquares(); // Sum of Squares
        f8.getVariance(); // Variance

        FacetBuilders.termsStatsFacet("f").keyField("brand").valueField("price");

        // sr is here your SearchResponse object
        TermsStatsFacet f9 = (TermsStatsFacet) sr.getFacets().facetsAsMap().get("f");
        // f9.getTotalCount(); // Total terms doc count
        // f9.getOtherCount(); // Not shown terms doc count
        f9.getMissingCount(); // Without term doc count

        // For each entry
        for (TermsStatsFacet.Entry entry : f9) {
            entry.getTerm(); // Term
            entry.getCount(); // Doc count
            entry.getMin(); // Min value
            entry.getMax(); // Max value
            entry.getMean(); // Mean
            entry.getTotal(); // Sum of values
        }

        FacetBuilders.geoDistanceFacet("f").field("pin.location") // Field
                                                                  // containing
                                                                  // coordinates
                                                                  // we want to
                                                                  // compare
                                                                  // with
                .point(40, -70) // Point from where we start (0)
                .addUnboundedFrom(10) // 0 to 10 km (excluded)
                .addRange(10, 20) // 10 to 20 km (excluded)
                .addRange(20, 100) // 20 to 100 km (excluded)
                .addUnboundedTo(100) // from 100 km to infinity (and beyond ;-)
                                     // )
                .unit(DistanceUnit.KILOMETERS); // All distances are in
                                                // kilometers. Can be MILES

        // sr is here your SearchResponse object
        GeoDistanceFacet f11 = (GeoDistanceFacet) sr.getFacets().facetsAsMap().get("f");

        // For each entry
        for (GeoDistanceFacet.Entry entry : f11) {
            entry.getFrom(); // Distance from requested
            entry.getTo(); // Distance to requested
            entry.getCount(); // Doc count
            entry.getMin(); // Min value
            entry.getMax(); // Max value
            entry.getTotal(); // Sum of values
            entry.getMean(); // Mean
        }

        FacetBuilders.termsFacet("f").field("brand") // Your facet
                .facetFilter( // Your filter here
                        FilterBuilders.termFilter("colour", "pale"));

        // A common filter
        FilterBuilder filter = FilterBuilders.termFilter("colour", "pale");

        TermsFacetBuilder facet1 = FacetBuilders.termsFacet("f").field("brand").facetFilter(filter); // We
                                                                                                     // apply
                                                                                                     // it
                                                                                                     // to
                                                                                                     // the
                                                                                                     // facet

        SearchResponse sr1 = client.prepareSearch().setQuery(QueryBuilders.matchAllQuery()).setFilter(filter) // We
                                                                                                              // apply
                                                                                                              // it
                                                                                                              // to
                                                                                                              // the
                                                                                                              // query
                .addFacet(facet).execute().actionGet();

        TermsFacetBuilder facet2 = FacetBuilders.termsFacet("f").field("brand").global(true);

    }

    private void testPercolate() throws IOException {

        // This is the query we're registering in the percolator
        QueryBuilder qb = termQuery("content", "amazing");

        // Index the query = register it in the percolator
        client.prepareIndex("_percolator", "myIndexName", "myDesignatedQueryName") //
                .setSource(qb.buildAsBytes()) //
                .setRefresh(true) // Needed when the query shall be available
                                  // immediately
                .execute().actionGet();

        // Build a document to check against the percolator
        XContentBuilder docBuilder = jsonBuilder().startObject();
        docBuilder.field("doc").startObject(); // This is needed to designate
                                               // the document
        docBuilder.field("content", "This is amazing!");
        docBuilder.endObject(); // End of the doc field
        docBuilder.endObject(); // End of the JSON root object
        // Percolate
        PercolateResponse response = client.preparePercolate("myIndexName", "myDocumentType") //
                .setSource(docBuilder) //
                .execute() //
                .actionGet();
        // Iterate over the results
        for (String result : response) {
            // Handle the result which is the name of
            // the query in the percolator
        }
    }

    private static Client client(Node[] nodes) {
        return nodes.length == 1 ? nodes[0].client() : nodes[1].client();
    }

    private static Client client(Settings indexSettings, String host, int port) {
        return new TransportClient(indexSettings) //
                .addTransportAddress(new InetSocketTransportAddress(host, port));
    }

}
