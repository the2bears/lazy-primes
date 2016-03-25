;;   Copyright (c) William Swaney. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns lazy-primes.core
  (:gen-class))

(def sieve (atom (iterate inc 2)));lazy sequence via iterate

(defn remove-factors [factor s]
  "Filters the factor from the sequence"
  (filter (fn[x] (not= 0 (mod x factor))) s))

(defn next-prime []
  "Return the front of the sieve, which is the next prime"
  (let [p (first @sieve)
        modified-sieve  (remove-factors p @sieve)]
    (reset! sieve modified-sieve)
    p))

