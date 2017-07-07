;; gorilla-repl.fileformat = 1

;; **
;;; # Gorilla Graph
;;; 
;;; This worksheet demonstrates the usage of gorilla-graph. Graphs are displayed using [vis.js](http://visjs.org/) networks.
;; **

;; @@
(ns barren-briars
  (:require [gorilla-graph.core :as graph]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; ## Simple Maps of Nodes and Edges
;; **

;; @@
(graph/view {:nodes [:a :b :c :d :e :f :g]
   			 :edges [[:d :b] [:b :c] [:d :c] [:e :f] [:f :g]
           			 [:d :a] [:f :c]]})
;; @@
;; =>
;;; {"type":"html","content":"<div id='f424df66-e55a-4bee-9dc3-702bb5de61b8'></div>\n<script type='text/javascript'>\n$(function () {\n  var cachedScript = function(url, options) {\n    // Allow user to set any option except for dataType, cache, and url\n    options = $.extend( options || {}, {\n      dataType: 'script',\n      cache: true,\n      url: url\n    });\n    // Use $.ajax() since it is more flexible than $.getScript\n    // Return the jqXHR object so we can chain callbacks\n    return jQuery.ajax(options);\n  };\n  var createNetwork = function() {\n    // create an array with nodes\n    var nodes = new vis.DataSet([{\"label\":\"a\",\"id\":\"a\"},{\"label\":\"b\",\"id\":\"b\"},{\"label\":\"c\",\"id\":\"c\"},{\"label\":\"d\",\"id\":\"d\"},{\"label\":\"e\",\"id\":\"e\"},{\"label\":\"f\",\"id\":\"f\"},{\"label\":\"g\",\"id\":\"g\"}]);\n\n    // create an array with edges\n    var edges = new vis.DataSet([{\"from\":\"d\",\"to\":\"b\"},{\"from\":\"b\",\"to\":\"c\"},{\"from\":\"d\",\"to\":\"c\"},{\"from\":\"e\",\"to\":\"f\"},{\"from\":\"f\",\"to\":\"g\"},{\"from\":\"d\",\"to\":\"a\"},{\"from\":\"f\",\"to\":\"c\"}]);\n\n    // create a network\n    var container = document.getElementById('f424df66-e55a-4bee-9dc3-702bb5de61b8');\n\n    // provide the data in the vis format\n    var data = {nodes: nodes, edges: edges};\n    var options = {\"width\":\"400px\",\"height\":\"400px\"};\n\n    // initialize your network!\n    var network = new vis.Network(container, data, options);\n  };\n  if (!document.getElementById('vis-css')) {\n    $('<link>')\n      .attr('rel', 'stylesheet')\n      .attr('href', 'https://cdnjs.cloudflare.com/ajax/libs/vis/4.20.0/vis.min.css')\n      .attr('id', 'vis-css')\n      .appendTo('head');\n  }\n  if (!window.visJsLoaded) {\n    if (!window.visJsIsLoading) {\n      window.visJsLoadedCallbacks = [createNetwork];\n      window.visJsIsLoading = true;\n      cachedScript('https://cdnjs.cloudflare.com/ajax/libs/vis/4.20.0/vis.min.js')\n        .done(function() {\n          window.visJsIsLoading = false;\n          window.visJsLoaded = true;\n          _.each(window.visJsLoadedCallbacks, function(cb) { cb(); });\n          window.visJsLoadedCallbacks = [];\n        })\n        .fail(function() { console.log('failed'); });\n    } else {\n      window.visJsLoadedCallbacks.push(createNetwork);\n    }\n  } else {\n    createNetwork();\n  }\n});\n</script>","value":"#gorilla_graph.core.GraphView{:graph-data {:nodes [:a :b :c :d :e :f :g], :edges [[:d :b] [:b :c] [:d :c] [:e :f] [:f :g] [:d :a] [:f :c]]}, :options {:width \"400px\", :height \"400px\"}}"}
;; <=

;; **
;;; ## Full vis.js Customization
;;; 
;;; The full customization power of vis.js is supported. See the [vis.js documentation](http://visjs.org/docs/network/) for information on [options](http://visjs.org/docs/network/), [nodes](http://visjs.org/docs/network/nodes.html), or [edges](http://visjs.org/docs/network/edges.html).
;; **

;; @@
; a groups example

(let [nodes [{:id 0 :label "0" :group 0}
             {:id 1 :label "1" :group 0}
             {:id 2 :label "2" :group 0}
             {:id 3 :label "3" :group 1}
             {:id 4 :label "4" :group 1}
             {:id 5 :label "5" :group 1}
             {:id 6 :label "6" :group 2}
             {:id 7 :label "7" :group 2}
             {:id 8 :label "8" :group 2}
             {:id 9 :label "9" :group 3}
             {:id 10 :label "10" :group 3}
             {:id 11 :label "11" :group 3}
             {:id 12 :label "12" :group 4}
             {:id 13 :label "13" :group 4}
             {:id 14 :label "14" :group 4}
             {:id 15 :label "15" :group 5}
             {:id 16 :label "16" :group 5}
             {:id 17 :label "17" :group 5}
             {:id 18 :label "18" :group 6}
             {:id 19 :label "19" :group 6}
             {:id 20 :label "20" :group 6}
             {:id 21 :label "21" :group 7}
             {:id 22 :label "22" :group 7}
             {:id 23 :label "23" :group 7}
             {:id 24 :label "24" :group 8}
             {:id 25 :label "25" :group 8}
             {:id 26 :label "26" :group 8}
             {:id 27 :label "27" :group 9}
             {:id 28 :label "28" :group 9}
             {:id 29 :label "29" :group 9}]
      edges [[1 0] [2 0] [4 3] [5 4] [4 0] [7 6] [8 7] [7 0] [10 9] [11 10]
             [10 4] [13 12] [14 13] [13 0] [16 15] [17 15] [15 10] [19 18]
             [20 19] [19 4] [22 21] [23 22] [22 13] [25 24] [26 25] [25 7]
             [28 27] [29 28] [28 0]]]
  (graph/view
    {:nodes nodes :edges edges}
    ;; Options
    {:nodes {:shape "dot" :size 30 :font {:size 32 :color "#111111"}
             :borderWidth 2}
     :edges {:width 2}}))
;; @@
;; =>
;;; {"type":"html","content":"<div id='228130df-629d-428b-9837-9e72ce238e3e'></div>\n<script type='text/javascript'>\n$(function () {\n  var cachedScript = function(url, options) {\n    // Allow user to set any option except for dataType, cache, and url\n    options = $.extend( options || {}, {\n      dataType: 'script',\n      cache: true,\n      url: url\n    });\n    // Use $.ajax() since it is more flexible than $.getScript\n    // Return the jqXHR object so we can chain callbacks\n    return jQuery.ajax(options);\n  };\n  var createNetwork = function() {\n    // create an array with nodes\n    var nodes = new vis.DataSet([{\"id\":0,\"label\":\"0\",\"group\":0},{\"id\":1,\"label\":\"1\",\"group\":0},{\"id\":2,\"label\":\"2\",\"group\":0},{\"id\":3,\"label\":\"3\",\"group\":1},{\"id\":4,\"label\":\"4\",\"group\":1},{\"id\":5,\"label\":\"5\",\"group\":1},{\"id\":6,\"label\":\"6\",\"group\":2},{\"id\":7,\"label\":\"7\",\"group\":2},{\"id\":8,\"label\":\"8\",\"group\":2},{\"id\":9,\"label\":\"9\",\"group\":3},{\"id\":10,\"label\":\"10\",\"group\":3},{\"id\":11,\"label\":\"11\",\"group\":3},{\"id\":12,\"label\":\"12\",\"group\":4},{\"id\":13,\"label\":\"13\",\"group\":4},{\"id\":14,\"label\":\"14\",\"group\":4},{\"id\":15,\"label\":\"15\",\"group\":5},{\"id\":16,\"label\":\"16\",\"group\":5},{\"id\":17,\"label\":\"17\",\"group\":5},{\"id\":18,\"label\":\"18\",\"group\":6},{\"id\":19,\"label\":\"19\",\"group\":6},{\"id\":20,\"label\":\"20\",\"group\":6},{\"id\":21,\"label\":\"21\",\"group\":7},{\"id\":22,\"label\":\"22\",\"group\":7},{\"id\":23,\"label\":\"23\",\"group\":7},{\"id\":24,\"label\":\"24\",\"group\":8},{\"id\":25,\"label\":\"25\",\"group\":8},{\"id\":26,\"label\":\"26\",\"group\":8},{\"id\":27,\"label\":\"27\",\"group\":9},{\"id\":28,\"label\":\"28\",\"group\":9},{\"id\":29,\"label\":\"29\",\"group\":9}]);\n\n    // create an array with edges\n    var edges = new vis.DataSet([{\"from\":1,\"to\":0},{\"from\":2,\"to\":0},{\"from\":4,\"to\":3},{\"from\":5,\"to\":4},{\"from\":4,\"to\":0},{\"from\":7,\"to\":6},{\"from\":8,\"to\":7},{\"from\":7,\"to\":0},{\"from\":10,\"to\":9},{\"from\":11,\"to\":10},{\"from\":10,\"to\":4},{\"from\":13,\"to\":12},{\"from\":14,\"to\":13},{\"from\":13,\"to\":0},{\"from\":16,\"to\":15},{\"from\":17,\"to\":15},{\"from\":15,\"to\":10},{\"from\":19,\"to\":18},{\"from\":20,\"to\":19},{\"from\":19,\"to\":4},{\"from\":22,\"to\":21},{\"from\":23,\"to\":22},{\"from\":22,\"to\":13},{\"from\":25,\"to\":24},{\"from\":26,\"to\":25},{\"from\":25,\"to\":7},{\"from\":28,\"to\":27},{\"from\":29,\"to\":28},{\"from\":28,\"to\":0}]);\n\n    // create a network\n    var container = document.getElementById('228130df-629d-428b-9837-9e72ce238e3e');\n\n    // provide the data in the vis format\n    var data = {nodes: nodes, edges: edges};\n    var options = {\"width\":\"400px\",\"height\":\"400px\",\"nodes\":{\"shape\":\"dot\",\"size\":30,\"font\":{\"size\":32,\"color\":\"#111111\"},\"borderWidth\":2},\"edges\":{\"width\":2}};\n\n    // initialize your network!\n    var network = new vis.Network(container, data, options);\n  };\n  if (!document.getElementById('vis-css')) {\n    $('<link>')\n      .attr('rel', 'stylesheet')\n      .attr('href', 'https://cdnjs.cloudflare.com/ajax/libs/vis/4.20.0/vis.min.css')\n      .attr('id', 'vis-css')\n      .appendTo('head');\n  }\n  if (!window.visJsLoaded) {\n    if (!window.visJsIsLoading) {\n      window.visJsLoadedCallbacks = [createNetwork];\n      window.visJsIsLoading = true;\n      cachedScript('https://cdnjs.cloudflare.com/ajax/libs/vis/4.20.0/vis.min.js')\n        .done(function() {\n          window.visJsIsLoading = false;\n          window.visJsLoaded = true;\n          _.each(window.visJsLoadedCallbacks, function(cb) { cb(); });\n          window.visJsLoadedCallbacks = [];\n        })\n        .fail(function() { console.log('failed'); });\n    } else {\n      window.visJsLoadedCallbacks.push(createNetwork);\n    }\n  } else {\n    createNetwork();\n  }\n});\n</script>","value":"#gorilla_graph.core.GraphView{:graph-data {:nodes [{:id 0, :label \"0\", :group 0} {:id 1, :label \"1\", :group 0} {:id 2, :label \"2\", :group 0} {:id 3, :label \"3\", :group 1} {:id 4, :label \"4\", :group 1} {:id 5, :label \"5\", :group 1} {:id 6, :label \"6\", :group 2} {:id 7, :label \"7\", :group 2} {:id 8, :label \"8\", :group 2} {:id 9, :label \"9\", :group 3} {:id 10, :label \"10\", :group 3} {:id 11, :label \"11\", :group 3} {:id 12, :label \"12\", :group 4} {:id 13, :label \"13\", :group 4} {:id 14, :label \"14\", :group 4} {:id 15, :label \"15\", :group 5} {:id 16, :label \"16\", :group 5} {:id 17, :label \"17\", :group 5} {:id 18, :label \"18\", :group 6} {:id 19, :label \"19\", :group 6} {:id 20, :label \"20\", :group 6} {:id 21, :label \"21\", :group 7} {:id 22, :label \"22\", :group 7} {:id 23, :label \"23\", :group 7} {:id 24, :label \"24\", :group 8} {:id 25, :label \"25\", :group 8} {:id 26, :label \"26\", :group 8} {:id 27, :label \"27\", :group 9} {:id 28, :label \"28\", :group 9} {:id 29, :label \"29\", :group 9}], :edges [[1 0] [2 0] [4 3] [5 4] [4 0] [7 6] [8 7] [7 0] [10 9] [11 10] [10 4] [13 12] [14 13] [13 0] [16 15] [17 15] [15 10] [19 18] [20 19] [19 4] [22 21] [23 22] [22 13] [25 24] [26 25] [25 7] [28 27] [29 28] [28 0]]}, :options {:nodes {:shape \"dot\", :size 30, :font {:size 32, :color \"#111111\"}, :borderWidth 2}, :edges {:width 2}}}"}
;; <=

;; @@

;; @@
