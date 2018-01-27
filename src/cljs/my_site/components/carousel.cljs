(ns my-site.components.carousel
  (:require [reagent.core :as r]))

;; Private helpers

(defn- get-title 
  [idx total]
  (str (inc idx) " / " total))

(defn- show-slide [label current])

(defn- assign-labels
  "Takes a map of image data and assign indexing labels under the :label key."
  [images]
  (let [length (count images)
        assign-label (fn [idx image] (assoc image :idx idx 
                                                  :label (get-title idx length)))]
    (map-indexed assign-label images)))

(defn- get-element [id] (.getElementById js/document id))

(defn- pos-dec [val]
  "returns dec if val is positive, else identity"
  (if (pos? val) dec identity))

;; Data

(def data [{:path "/images/flex.png" 
            :caption "Auto-caching and easy JSON transformation 
                      of API endpoints in Python"}
           {:path "/images/site.png"
            :caption "Single Page Application built in Reagent, 
                      a ClojureScript wrapper around ReactJS."}])

(def labeled-images (assign-labels data))

;; UI
(defn slide-class 
  [idx counter]
    (if (= idx counter)
      "mySlides fade active active-slide" 
      "mySlides fade inactive-slide"))
  
(defn- slide
  "Generates individual slide html component for a carousel."
  [counter {:keys [idx path label caption]}]
  (let [cls (slide-class idx counter)]
    ^{:key idx}
    [:div {:class cls} 
      [:div {:class "numbertext"} label]
      [:img {:src path 
             :style {:width "100%"}}]
      [:div {:class "text"} caption]]))

;(def slides (map slide images-with-labels))
(defn bound-inc 
  [counter]
  (let [len (count labeled-images)
        max? (= @counter (dec len))]
    (when (not max?)
      (swap! counter inc))))

(defn bound-dec
  [counter]
  (let [min? (= @counter 0)]
    (when (not min?)
      (swap! counter dec))))

(defn carousel []
  (let [counter (r/atom 0)]
    (add-watch counter :key (fn [k atm old new] (println @atm)))
    (fn []
      [:div 
        [:div {:class "slideshow-container"}
          (doall (for [image labeled-images] 
            (slide @counter image)))
          [:a {:class "prev chevron left" 
               :on-click #(bound-dec counter)}]
          [:a {:class "next chevron right" 
               :on-click #(bound-inc counter)}]]])))




