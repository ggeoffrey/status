(ns status.protocols.http
  (:require [status.core :as status]
            [clojure.string :as str]))

(derive ::info ::http)
(derive ::success ::http)
(derive ::redirection ::http)
(derive ::client-error ::http)
(derive ::server-error ::http)

(derive ::info         ::status/info)
(derive ::success      ::status/success)
(derive ::redirection  ::http)
(derive ::client-error ::status/error)
(derive ::server-error ::status/error)

(derive ::continue            ::info)
(derive ::switching-protocols ::info)
(derive ::processing          ::info)
(derive ::early-hints         ::info)

(derive ::ok                            ::success)
(derive ::created                       ::success)
(derive ::accepted                      ::success)
(derive ::non-authoritative-information ::success)
(derive ::no-content                    ::success)
(derive ::reset-content                 ::success)
(derive ::partial-content               ::success)
(derive ::multi-statuts                 ::success)
(derive ::already-reported              ::success)
(derive ::im-used                       ::success)

(derive ::multiple-choices   ::redirection)
(derive ::moved-permanently  ::redirection)
(derive ::found              ::redirection)
(derive ::see-other          ::redirection)
(derive ::not-modified       ::redirection)
(derive ::use-proxy          ::redirection)
(derive ::switch-proxy       ::redirection)
(derive ::temporary-redirect ::redirection)
(derive ::permanent-redirect ::redirection)

(derive ::bad-request                     ::client-error)
(derive ::unauthorized                    ::client-error)
(derive ::payment-required                ::client-error)
(derive ::forbidden                       ::client-error)
(derive ::not-found                       ::client-error)
(derive ::method-not-allowed              ::client-error)
(derive ::not-acceptable                  ::client-error)
(derive ::proxy-authentication-required   ::client-error)
(derive ::request-timeout                 ::client-error)
(derive ::conflict                        ::client-error)
(derive ::gone                            ::client-error)
(derive ::length-required                 ::client-error)
(derive ::precondition-failed             ::client-error)
(derive ::payload-too-large               ::client-error)
(derive ::uri-too-long                    ::client-error)
(derive ::unsupported-media-type          ::client-error)
(derive ::range-not-satisfiable           ::client-error)
(derive ::expectation-failed              ::client-error)
(derive ::i-m-a-teapot                    ::client-error)
(derive ::misdirected-request             ::client-error)
(derive ::unprocessable-entity            ::client-error)
(derive ::locked                          ::client-error)
(derive ::failed-dependency               ::client-error)
(derive ::upgrade-required                ::client-error)
(derive ::precondition-required           ::client-error)
(derive ::too-many-requests               ::client-error)
(derive ::request-header-fields-too-large ::client-error)
(derive ::unavailable-for-leagal-reasons  ::client-error)

(derive ::internal-server-error           ::server-error)
(derive ::not-implemented                 ::server-error)
(derive ::bad-gateway                     ::server-error)
(derive ::service-unavailable             ::server-error)
(derive ::gateway-timeout                 ::server-error)
(derive ::http-version-not-supported      ::server-error)
(derive ::variant-also-negotiates         ::server-error)
(derive ::insufficient-storage            ::server-error)
(derive ::loop-detected                   ::server-error)
(derive ::not-extended                    ::server-error)
(derive ::network-authentication-required ::server-error)

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

(defmethod status/message ::http [status]
  (str "HTTP " (status->code status) " " (-> (name status)
                                             (str/replace #"-" " ")
                                             (str/capitalize))))

;; (ancestors (code->status 404))
;; (->> 404 code->status status/message)
