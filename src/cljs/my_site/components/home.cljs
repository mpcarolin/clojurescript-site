(ns my-site.components.home
  (:require [my-site.components.carousel :refer [carousel]]))

(def title "MICHAEL CAROLIN")

(defn home-page []
  [:div
    [:div.center-column
      [:h1.center-text title]
      [:h3.center-text "Software Engineer"]

      [:video.rounded-video {:loop "true"
                             :autoPlay "true"
                             :width 640
                             :height 360
                             :title "ClojureScript programming"
                             :name "ClojureScript programming"}
       [:source {:src "/video/coding.mov"
                 :type "video/mp4"}]]

      ;;[:h4 "Projects"]
      ;;[carousel]
      ]])

