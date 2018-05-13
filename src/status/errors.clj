(ns status.errors)

(def statuses (merge {::error :status/status}))

(comment
  {::authentication ::error
   ::forbidden      ::authentication
   ::not-yours      ::forbidden}
  {::business-logic ::error
   ::not-found      ::business-logic
   ::monadic        ::business-logic
   ::unknown        ::monadic}
  {::validation        ::error
   ::illegal-parameter ::validation}
  {::feature         ::error
   ::not-implemented ::feature})
