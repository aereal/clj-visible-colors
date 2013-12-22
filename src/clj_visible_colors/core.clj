(ns clj-visible-colors.core
  [:require [com.github.bdesham.clj-plist :refer :all]])

(defrecord Color [red green blue])

(defn make-color-object-from-values
  [rgb]
  (apply #(->Color %1 %2 %3) rgb))

(defn parse-color-scheme
  [f]
  (let [plist (parse-plist (java.io.File. f))]
    (map #(make-color-object-from-values (vals %)) (vals plist))))

(defn brighness-of-component
  [c v]
  (let [k {:red 299 :green 587 :blue 114}]
    (* 255 v (c k))))

(defn brightness
  [c]
  (reduce + (map #(brighness-of-component (key %) (val %)) c)))

(defn has-visible-brightness-contrast
  [ca cb]
  <= 125 (. Math abs (reduce - (map #(brightness %) (list ca cb)))))

(defn hue-contrast
  [ca cb]
  (* 255 (reduce + (map #(- (max (% ca) (% cb)) (min (% ca) (% cb))) (list :red :green :blue)))))

(defn has-visible-hue-contrast
  [ca cb]
  (<= 500 (hue-contrast ca cb)))
