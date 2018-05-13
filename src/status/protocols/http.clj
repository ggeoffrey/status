(ns status.protocols.http
  (:require [status.errors :as err]
            [status.success :as success]
            [status.info :as info]
            [status.utils :as utils]
            [clojure.string :as str]))

(derive ::info ::http)
(derive ::success ::http)
(derive ::redirection ::http)
(derive ::client-error ::http)
(derive ::server-error ::http)

(def statuses {::info         ::info/info
               ::success      ::success/success
               ::redirection  ::http
               ::client-error ::err/error
               ::server-error ::err/error

               ::continue            ::info
               ::switching-protocols ::info
               ::processing          ::info
               ::early-hints         ::info

               ::ok                            ::success
               ::created                       ::success
               ::accepted                      ::success
               ::non-authoritative-information ::success
               ::no-content                    ::success
               ::reset-content                 ::success
               ::partial-content               ::success
               ::multi-statuts                 ::success
               ::already-reported              ::success
               ::im-used                       ::success

               ::multiple-choices   ::redirection
               ::moved-permanently  ::redirection
               ::found              ::redirection
               ::see-other          ::redirection
               ::not-modified       ::redirection
               ::use-proxy          ::redirection
               ::switch-proxy       ::redirection
               ::temporary-redirect ::redirection
               ::permanent-redirect ::redirection

               ::bad-request                     ::client-error
               ::unauthorized                    ::client-error
               ::payment-required                ::client-error
               ::forbidden                       ::client-error
               ::not-found                       ::client-error
               ::method-not-allowed              ::client-error
               ::not-acceptable                  ::client-error
               ::proxy-authentication-required   ::client-error
               ::request-timeout                 ::client-error
               ::conflict                        ::client-error
               ::gone                            ::client-error
               ::length-required                 ::client-error
               ::precondition-failed             ::client-error
               ::payload-too-large               ::client-error
               ::uri-too-long                    ::client-error
               ::unsupported-media-type          ::client-error
               ::range-not-satisfiable           ::client-error
               ::expectation-failed              ::client-error
               ::i-m-a-teapot                    ::client-error
               ::misdirected-request             ::client-error
               ::unprocessable-entity            ::client-error
               ::locked                          ::client-error
               ::failed-dependency               ::client-error
               ::upgrade-required                ::client-error
               ::precondition-required           ::client-error
               ::too-many-requests               ::client-error
               ::request-header-fields-too-large ::client-error
               ::unavailable-for-leagal-reasons  ::client-error

               ::internal-server-error           ::server-error
               ::not-implemented                 ::server-error
               ::bad-gateway                     ::server-error
               ::service-unavailable             ::server-error
               ::gateway-timeout                 ::server-error
               ::http-version-not-supported      ::server-error
               ::variant-also-negotiates         ::server-error
               ::insufficient-storage            ::server-error
               ::loop-detected                   ::server-error
               ::not-extended                    ::server-error
               ::network-authentication-required ::server-error})

(def code->status {100 ::continue
                   101 ::switching-protocols
                   102 ::processing
                   103 ::early-hints
                   200 ::ok
                   201 ::created
                   202 ::accepted
                   203 ::non-authoritative-information
                   204 ::no-content
                   205 ::reset-content
                   206 ::partial-content
                   207 ::multi-statuts
                   208 ::already-reported
                   226 ::im-used
                   300 ::multiple-choices
                   301 ::moved-permanently
                   302 ::found
                   303 ::see-other
                   304 ::not-modified
                   305 ::use-proxy
                   306 ::switch-proxy
                   307 ::temporary-redirect
                   308 ::permanent-redirect
                   400 ::bad-request
                   401 ::unauthorized
                   402 ::payment-required
                   403 ::forbidden
                   404 ::not-found
                   405 ::method-not-allowed
                   406 ::not-acceptable
                   407 ::proxy-authentication-required
                   408 ::request-timeout
                   409 ::conflict
                   410 ::gone
                   411 ::length-required
                   412 ::precondition-failed
                   413 ::payload-too-large
                   414 ::uri-too-long
                   415 ::unsupported-media-type
                   416 ::range-not-satisfiable
                   417 ::expectation-failed
                   418 ::i-m-a-teapot
                   421 ::misdirected-request
                   422 ::unprocessable-entity
                   423 ::locked
                   424 ::failed-dependency
                   426 ::upgrade-required
                   428 ::precondition-required
                   429 ::too-many-requests
                   431 ::request-header-fields-too-large
                   451 ::unavailable-for-leagal-reasons
                   500 ::internal-server-error
                   501 ::not-implemented
                   502 ::bad-gateway
                   503 ::service-unavailable
                   504 ::gateway-timeout
                   505 ::http-version-not-supported
                   506 ::variant-also-negotiates
                   507 ::insufficient-storage
                   508 ::loop-detected
                   510 ::not-extended
                   511 ::network-authentication-required})

(def status->code (->> code->status (map (fn [[k v]] [v k])) (into {})))

(defmethod utils/message ::http [status]
  (str "HTTP " (status->code status) " " (-> (name status)
                                             (str/replace #"-" " ")
                                             (str/capitalize))))

