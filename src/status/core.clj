(ns status.core)

(derive ::info :status/status)
(derive ::success :status/status)
(derive ::error :status/status)

(defmulti message identity)

;; (message ::http/not-found)
;; (ancestors ::http/switching-protocols)
;; (message :status)

;; (comment
;;   (-> (http/code->status 404)
;;       (isa? ::http/client-error))
;;   (->> (http/code->status 404)
;;        (ancestors hierarchy))

;;   (ancestors hierarchy ::http/forbidden)
;;   (ancestors hierarchy ::errors/not-yours)
;;   (clojure.pprint/pprint hierarchy)
;;   )
