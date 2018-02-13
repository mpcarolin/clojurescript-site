(ns my-site.components.about)

(defonce link [:a {:href "https://clojure.org/"
               :target "_blank"} 
           "Clojure"])

(defonce git "https://github.com/mpcarolin")
(defonce linkedin "https://www.linkedin.com/in/mpcarolin/")

(defn bio
  [link]
  [:p "I am a developer currently working at Raytheon in Tucson, AZ. I focus on
       full stack web development, but I am also beginning work on Hololens AR applications.
       I am passionate about environmentalism, science, programming, and literature. Lately, I've grown
       obsessed with the programming language " [:span link] " (this website was built with it!) 
       and functional programming in general. When I'm not coding, you can find me playing guitar, 
       losing at board games, or trying my luck in a harrowing dungeons and dragons campaign."])

(defn about-page []
  [:div.center-column
   [:div.about-container
     [:img.portrait {:src "/images/portrait.jpg"}]
     [:div.link-container
       [:h4.left-link [:a {:href git} "Github"]]
       [:h4.spacer "•"]
       [:h4.right-link [:a {:href linkedin} "LinkedIn"]]]]
   [:br]
   [bio link]
   [:div.about-right
     [:h4 "Education"]
     [:ul
       [:li "B.S. Computer Science, University of Arizona"]
       [:li "B.A. English, University of Arizona"]]]
   [:div.about-left
     [:h4 "Languages"]
     [:ul
      [:li "Java"]
      [:li "JavaScript"]
      [:li "Python"]
      [:li "Clojure"]
      [:li "C"]
      [:li "C#"]]]])
