(ns my-site.components.home
  (:require [my-site.components.carousel :refer [carousel]]))

;;[:img.git {:src "/images/github-120.png"}]

(defn home-page []
  [:div
    [:div.center-column
      [:h1.center-text "MICHAEL CAROLIN"]
      [:h3.center-text "Software Engineer"]
      [:div
        [:video.rounded-video {:loop "true"
                               :autoPlay "true"
                               :width 640
                               :height 360
                               :title "ClojureScript programming"
                               :name "ClojureScript programming"}
         [:source {:src "/video/coding.mov"
                   :type "video/mp4"}]]]]])

