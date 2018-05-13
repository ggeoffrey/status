(ns user
  (:require [reloaded.repl :as repl]
            [com.stuartsierra.component :as cmpts]))

(repl/set-init! (constantly (cmpts/system-map)))
