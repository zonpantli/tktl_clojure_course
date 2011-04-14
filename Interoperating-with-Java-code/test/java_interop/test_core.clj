(ns java-interop.test-core
  (:use java-interop.core
        midje.sweet)
  (:import [java.util Comparator Collections]))

(facts "normalize"
       (normalize "MAHTIFANTTI")     => "mahtifantti"
       (normalize "   Muhkufantti ") => "muhkufantti"
       (normalize "")                => ""
       (normalize "    ")            => ""
       (normalize " c l j ")         => "c l j")

(facts "randoms"
       (randoms 1 3)
         => [-1155869325 431529176 1761283695]
       (randoms 1337 5)
         => [-1460590454 747279288 -1334692577 -539670452 -501340078])

(facts "arraylist-flatten"
       (seq (arraylist-flatten [])) => empty?
       (seq (arraylist-flatten [[]])) => empty?
       (seq (arraylist-flatten [[1 2]])) => [1 2]
       (seq (arraylist-flatten [[1 2 3] [4 5 6]])) => [1 2 3 4 5 6]
       (.getClass (arraylist-flatten [[1 2 3] [4 5 6]])) 
           => java.util.ArrayList)

(facts "callable-pony"
       (instance? Callable (callable-pony)) => true
       (.call (callable-pony)) => :pony)

(facts "minimum-str"
       (minimum-str #{"abc" "cat" ":D"}) => ":D"
       (minimum-str ["OMG" "ponies" ""]) => "")

(facts "comparing"
       (Collections/min #{"abc" "cat" ":D"} (comparing count)) => ":D"
       (.compare (comparing #(get {:a 1 :b 2} %)) :b :a) => 1
       (.compare (comparing #(get {:a 1 :b 2} %)) :a :b) => -1
       (instance? Comparator (comparing (partial * 2))) => true
       (Collections/min [:a :b :c] (comparing #(get {:a 3 :b 0 :c 1} %))) 
         => :b)

(facts "sum-array"
       (sum-array (to-array [1 2 3]))  => 6
       (sum-array (float-array [5 4])) => 9
       (sum-array (into-array []))     => 0)

(facts "fibo-array"
       (instance? (.getClass (into-array [1])) (fibo-array 5)) => true
       (seq (fibo-array 6)) => '(0 1 1 2 3 5)
       (seq (fibo-array 8)) => '(0 1 1 2 3 5 8 13))
