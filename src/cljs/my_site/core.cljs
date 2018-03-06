(ns my-site.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [my-site.components.home :refer [home-page]]
              [my-site.components.about :refer [about-page]]
              [my-site.components.blog :refer [blog-page]]
              [my-site.components.navigation :refer [nav-bar]]
              [markdown-to-hiccup.core :as m]
              [accountant.core :as accountant]))

;; -------------------------
;; Routes

(defonce page (atom #'home-page))
(defonce route (atom "/"))

(defn update-page!
  [next-page next-route]
  (reset! page next-page)
  (reset! route next-route))

(defn current-page []
  [:div.root-div
   [nav-bar @route]
   [@page]])

(secretary/defroute "/" []
  (update-page! #'home-page "/"))

(secretary/defroute "/about" []
  (update-page! #'about-page "/about"))

(secretary/defroute "/blog" []
  (update-page! #'blog-page "/blog/latest"))

(secretary/defroute "/blog/:title" [title]
  (update-page! #'blog-page (str "/blog" title)))
;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
