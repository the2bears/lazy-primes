;;   Copyright (c) William Swaney. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns lazy-primes.print
  (:gen-class)
  (:require [lazy-primes.core :as lp]))

(def tab-size 8)

(defn pad-number [n]
  "Returns a String of the number, pre-fixed with padding for proper spacing"
  (let [len (count (str n))
        pad (- tab-size len)]
    (str (apply str (repeat pad " ")) n)))

(defn print-row [num-seq]
  (doseq [s num-seq]
    (print (pad-number s)))
    (println))

(defn print-table [rows]
  (doseq [row rows]
    (print-row (flatten row))));Flatten to a List (2 4 6 10 14 22 26 34 38 46 58)

(defn generate-table [n]
  (let [first-n-primes (repeatedly n lp/next-prime);List
        table (for [x first-n-primes] (for [y first-n-primes] (* x y)));List of lists((1 2 3 4)(1 2 3 4)...)
        table-with-legend (map vector first-n-primes table)];List of Vectors ([2 (4 6 10 14 22 26 34 38 46 58)] [3 (6 9 15 21 33 39 51 57 69 87)]...)
    (print-row (cons " " first-n-primes))
    (print-table table-with-legend)))

(defn -main
  "Prints out a multiplication table of the first n primes."
  [& args]
  (let [n (try (Integer/valueOf (first args)) (catch NumberFormatException e 10))]
  (println "lazy-primes.print Multiplication Table of First" n "Primes")
  (generate-table n)))
