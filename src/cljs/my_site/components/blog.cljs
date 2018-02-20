(ns my-site.components.blog
  (:require [reagent.core :as r]
            [my-site.services.bootstrap :as b]))

;;
;; Data 
;;
(defn get-date [] (->> (js/Date.)
                       (.toDateString)))

(def date (get-date))

(defonce posts [{:title "Functional Programming is Practical"
                 :body [:p "In my opinion..."]
                 :date date}])

;;
;; Bootstrap bindings
;;
(defonce Grid (b/get-bootstrap "Grid"))
(defonce Row (b/get-bootstrap "Row"))
(defonce Col (b/get-bootstrap "Col"))

(comment
  (defn side-link
    [{:keys [link title]}]
    ^{:key link}
    [:div
      [:a.sidebar-text {:href link} title]])

  (defn side-bar []
    [:div.sidebar
     [:p.sidebar-title "History"]
     (for [link links]
       (side-link link))]))

(defn content
  [{:keys [title date body]}]
  [:div
    [:h2 title]
    [:hr]
    [:p.date date]
    body])

(defn blog-page
  [] ;; todo: pass a post into here
  [:div.center-column
   [:a {:href "/archive"
        :style {:font-size "0.75em"}}
    "archive"]
   (content (posts 0))])

