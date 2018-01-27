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

(def images-with-labels (assign-labels data))

(def current-slide (r/atom 1))

(defn next-slide [] 
  (swap! current-slide inc))
(defn prev-slide [] 
  (swap! current-slide (pos-dec @current-slide)))

;; UI
(defn active? [idx] (= idx @current-slide))

(defn slide-class 
  [idx]
    (if (active? idx)
      "mySlides fade active active-slide" 
      "mySlides fade inactive-slide"))
  
(defn- slide
  "Generates individual slide html component for a carousel."
  [{:keys [idx path label caption]}]
  (let [cls (slide-class idx)]
    ^{:key idx}
    [:div {:class cls} 
      [:div {:class "numbertext"} label]
      [:img {:src path 
             :style {:width "100%"}}]
      [:div {:class "text"} caption]]))

(def slides (map slide images-with-labels))

(defn carousel []
  [:div 
    [:div {:class "slideshow-container"}
      (doall (for [slide slides] slide))
      [:a {:class "prev chevron left" 
           :on-click prev-slide}]
      [:a {:class "next chevron right" 
           :on-click next-slide}]]])




