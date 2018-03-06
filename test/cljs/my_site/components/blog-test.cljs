(ns my-site.components.blog-test
  (:require [clojure.test :refer-macros [deftest is testing run-tests]]
            [my-site.components.blog :as blog]))

(deftest last-param
  (let [title "beratnas"
        url (str "http://mpcarolin.io/blog/" title)]
    (is (= title (blog/last-param url)))))
  
