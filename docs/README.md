# Module Graph

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  subgraph :data
    :data:api["api"]
  end
  subgraph :feature
    :feature:banks["banks"]
    :feature:account["account"]
  end
  :design --> :core
  :feature:banks --> :core
  :feature:banks --> :design
  :feature:banks --> :domain
  :feature:account --> :core
  :feature:account --> :design
  :feature:account --> :domain
  :data:api --> :core
  :data:api --> :domain
  :app --> :core
  :app --> :data:api
  :app --> :design
  :app --> :domain
  :app --> :feature:account
  :app --> :feature:banks

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
class :design android-library
class :core android-library
class :feature:banks android-library
class :domain android-library
class :feature:account android-library
class :data:api android-library
class :app android-application

```
# About

CATS - 2025

- Case study can be found in the repository: [docs/test_mobile_CA.pdf](test_mobile_CA.pdf)
- Project planning can be found too: [docs/project_planning.png](project_planning.png)