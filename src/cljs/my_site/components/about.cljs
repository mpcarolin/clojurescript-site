(ns my-site.components.about)

(defn about-page []
  [:div [:h2 "About my-site"]
   [:div [:a {:href "/"} "go to the home page"]]])