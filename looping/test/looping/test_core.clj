(ns looping.test-core
  (:use midje.sweet
        looping.core))

(facts "power"
       (power 2 2)  => 4
       (power 5 3)  => 125
       (power 7 0)  => 1
       (power 0 10) => 0)

(facts "last-element"
       (last-element [])      => nil?
       (last-element [1 2 3]) => 3
       (last-element [2 5])   => 5)

(facts "seq="
       (seq= [1 2 4] '(1 2 4))  => true
       (seq= [1 2 3] [1 2 3 4]) => false
       (seq= [1 3 5] [])        => false)

(facts "find-first-index"
       (find-first-index zero? [1 1 1 0 3 7 0 2])            => 3
       (find-first-index zero? [1 1 3 7 2])                  => nil
       (find-first-index #(= % 6) [:cat :dog :six :blorg 6]) => 4
       (find-first-index nil? [])                            => nil)

(facts "avg"
       (avg [1 2 3])   => 2
       (avg [0 0 0 4]) => 1
       (avg [1 0 0 1]) => (roughly 0.5))

(facts "parity"
       (parity [:a :b :c])            => (just [:a :b :c] :in-any-order)
       (parity [:a :b :c :a])         => (just [:b :c] :in-any-order)
       (parity [1 1 2 1 2 3 1 2 3 4]) => (just [2 4] :in-any-order))

(facts "fast-fibo"
       (fast-fibo 0) => 0
       (fast-fibo 1) => 1
       (fast-fibo 2) => 1
       (fast-fibo 3) => 2
       (fast-fibo 85) => 259695496911122585)

(facts "cut-at-repetition"
       (cut-at-repetition [1 1 1 1 1]) => [1]
       (cut-at-repetition [:cat :dog :house :milk 1 :cat :dog])
           => [:cat :dog :house :milk 1]
       (cut-at-repetition [0 1 2 3 4 5])
           => [0 1 2 3 4 5])
