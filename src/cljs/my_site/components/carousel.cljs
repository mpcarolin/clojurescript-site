(ns my-site.components.carousel
  (:require [reagent.core :as r]))

;;
;; Data
;;

(def data [{:path "/images/flex.png" 
            :uri "https://github.com/mpcarolin/PokeFlex"
            :caption "Auto-caching and easy JSON transformation 
                      of API endpoints in Python"}
           {:path "/images/site.png"
            :uri "https://github.com/mpcarolin/clojurescript-site"
            :caption "Single Page Application built in Reagent, 
                      a ClojureScript wrapper around ReactJS."}])


(defn- assign-labels
  "Takes a map of image data and assign indexing labels under the :label key."
  [images]
  (let [length (count images)
        get-title (fn [idx len] (str (inc idx) " / " len))
        assign-label (fn [idx image] (assoc image :idx idx 
                                                  :label (get-title idx length)))]
    (map-indexed assign-label images)))

(def labeled-images (assign-labels data))

(defn do-mod
  "Applies f to counter with modulo"
  [f counter modulo]
  (let [mod-f #(mod (f %) modulo)]
    (swap! counter mod-f)))

(def mod-inc (partial do-mod inc))
(def mod-dec (partial do-mod dec))

;;
;; UI Functions
;;


(defn slide-class 
  [idx counter]
    (if (= idx counter)
      "mySlides fade active active-slide" 
      "mySlides fade inactive-slide"))

;;
;; Components
;;
  
(defn- slide
  "Generates individual slide html component for a carousel."
  [{:keys [idx path uri label caption]} counter]
  (let [cls (slide-class idx counter)]
    ^{:key idx}
    [:div {:class cls} 
      [:div {:class "numbertext"} label]
      [:a {:href uri}
        [:img {:src path 
               :style {:width "100%"}}]]
      [:div {:class "text"} [:strong caption]]]))


(defn carousel []
  (let [counter (r/atom 0)
        length (count labeled-images)]
    (fn []
      [:div 
        [:div {:class "slideshow-container"}
          (doall (for [image labeled-images] 
            (slide image @counter)))
          [:a {:class "prev chevron left" 
               :on-click #(mod-dec counter length)}]
          [:a {:class "next chevron right" 
               :on-click #(mod-inc counter length)}]]])))
