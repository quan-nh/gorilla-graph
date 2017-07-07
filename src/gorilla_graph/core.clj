(ns gorilla-graph.core
  (:require [cheshire.core :as json]
            [clojure.string :as str]
            [gorilla-renderable.core :as render])
  (:import (java.util UUID)))

(defn- nodes->display-data
  "Converts a sequence of nodes into nodes for display."
  [nodes]
  (if (map? (first nodes))
    nodes
    (mapv #(hash-map :id % :label %) nodes)))

(defn- edges->display-data
  "Converts a sequence of edges into edges for display."
  [edges]
  (cond
    ;; Allows displaying a graph with no edges.
    (empty? edges)
    edges

    (map? (first edges))
    edges

    (sequential? (first edges))
    (mapv #(hash-map :from (first %) :to (second %)) edges)

    :else
    nil))

(def content-template
  "<div id='==network-id=='></div>
<script type='text/javascript'>
$(function () {
  var cachedScript = function(url, options) {
    // Allow user to set any option except for dataType, cache, and url
    options = $.extend( options || {}, {
      dataType: 'script',
      cache: true,
      url: url
    });
    // Use $.ajax() since it is more flexible than $.getScript
    // Return the jqXHR object so we can chain callbacks
    return jQuery.ajax(options);
  };
  var createNetwork = function() {
    // create an array with nodes
    var nodes = new vis.DataSet(==nodes==);

    // create an array with edges
    var edges = new vis.DataSet(==edges==);

    // create a network
    var container = document.getElementById('==network-id==');

    // provide the data in the vis format
    var data = {nodes: nodes, edges: edges};
    var options = ==options==;

    // initialize your network!
    var network = new vis.Network(container, data, options);
  };
  if (!document.getElementById('vis-css')) {
    $('<link>')
      .attr('rel', 'stylesheet')
      .attr('href', 'https://cdnjs.cloudflare.com/ajax/libs/vis/4.20.0/vis.min.css')
      .attr('id', 'vis-css')
      .appendTo('head');
  }
  if (!window.visJsLoaded) {
    if (!window.visJsIsLoading) {
      window.visJsLoadedCallbacks = [createNetwork];
      window.visJsIsLoading = true;
      cachedScript('https://cdnjs.cloudflare.com/ajax/libs/vis/4.20.0/vis.min.js')
        .done(function() {
          window.visJsIsLoading = false;
          window.visJsLoaded = true;
          _.each(window.visJsLoadedCallbacks, function(cb) { cb(); });
          window.visJsLoadedCallbacks = [];
        })
        .fail(function() { console.log('failed'); });
    } else {
      window.visJsLoadedCallbacks.push(createNetwork);
    }
  } else {
    createNetwork();
  }
});
</script>")

(def default-options
  {:width  400
   :height 400})

(defn render
  ([graph-data] (render graph-data {}))
  ([graph-data opts]
   (let [network-id (str (UUID/randomUUID))
         nodes (nodes->display-data (:nodes graph-data))
         edges (edges->display-data (:edges graph-data))
         options (merge default-options
                        opts)]
     (-> content-template
         (str/replace #"==network-id==" network-id)
         (str/replace #"==nodes==" (json/generate-string nodes))
         (str/replace #"==edges==" (json/generate-string edges))
         (str/replace #"==options==" (json/generate-string options))))))

;; * Gorilla REPL rendering *
(defrecord GraphView [graph-data options])

(extend-type GraphView
  render/Renderable
  (render [{:keys [graph-data options] :as self}]
    {:type :html :content (render graph-data options) :value (pr-str self)}))

(defn view
  ([graph-data] (view graph-data {}))
  ([graph-data options]
   (GraphView. graph-data options)))