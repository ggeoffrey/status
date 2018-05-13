(defproject status "0.1.0-SNAPSHOT"
  :description "Flexible status codes and descriptions, protocol and context agnostic."
  :url "https://github.com/ggeoffrey/status"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]

  :profiles {:dev {:dependencies [[reloaded.repl "0.2.4"]
                                  [com.stuartsierra/component "0.3.2"]]
                   :source-paths ["src" "dev" "test"]
                   :plugins      [[cider/cider-nrepl "0.18.0-SNAPSHOT"]
                                  [refactor-nrepl "2.4.0-SNAPSHOT"]]}})
