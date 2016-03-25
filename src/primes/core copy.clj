(ns primes.core
  (:gen-class))

(def sieve (atom (iterate inc 2)))

(deref sieve)

(defn remove-factors [factor s]
  "Filters the factor from the sequence"
  (filter (fn[x] (not= 0 (mod x factor))) s))

(defn next-prime []
  "Return the front of the sieve, which is the next prime"
  (let [p (first @sieve)
        modified-sieve  (remove-factors p (deref sieve))]
    (reset! sieve modified-sieve)
    p))

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
    (print-row (flatten row))))

(defn generate-table [n]
  (let [first-n-primes (repeatedly n next-prime)
        table (for [x first-n-primes] (for [y first-n-primes] (* x y)))
        table-with-legend (map vector first-n-primes table)]
    (print-row (cons " " first-n-primes))
    (print-table table-with-legend)))

(defn -main
  "Prints out a multiplication table of the first n primes."
  [& args]
  (let [n (try (Integer/valueOf (first args)) (catch NumberFormatException e 10))]
  (println "Multiplication Table of First" n "Primes")
  (generate-table n)))

(def first-10 (repeatedly 10 next-prime))
(def table (for [x first-10] (for [y first-10] (* x y))))
table
(def table-with-legend (map vector first-10 table))
table-with-legend
(flatten '[2 (4 6 10 14 22 26 34 38 46 58)])