(ns my-site.components.home
 	(:require [my-site.components.carousel :refer [carousel]]))

(def title "Michael Carolin")
(def git "https://github.com/mpcarolin")
(def linkedin "https://www.linkedin.com/in/mpcarolin/")

(defn home-page []
	[:div [:h1 title]
  		[:h4 "Software Engineer"]
  		[:ul 
  			[:li "B.S. Computer Science, University of Arizona"]
  			[:li "B.A. English, University of Arizona"]]
  		[:h4 "Links"]
  		[:ul 
  			[:li [:a {:href git} "Github" ]]
  			[:li [:a {:href linkedin} "LinkedIn"]]]
      [carousel]])