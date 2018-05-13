(ns status.core
  (:require [status.success :as success]
            [status.errors :as errors]
            [status.info :as info]))

(def statuses (merge info/statuses
                     success/statuses
                     errors/statuses))

(reduce (fn [_ [k v]]
          (derive k v))
        nil
        statuses)


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
