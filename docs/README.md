# Module Graph

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  subgraph :common
    :common:data["data"]
    :common:ui["ui"]
  end
  subgraph :feature
    :feature:banks["banks"]
    :feature:account["account"]
  end
  :feature:banks --> :core
  :feature:banks --> :common:data
  :feature:banks --> :common:ui
  :feature:banks --> :design
  :feature:account --> :core
  :feature:account --> :common:ui
  :feature:account --> :common:data
  :feature:account --> :design
  :common:data --> :core
  :common:ui --> :core
  :common:ui --> :design
  :app --> :core
  :app --> :design
  :app --> :feature:account
  :app --> :feature:banks

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
class :feature:banks android-library
class :core android-library
class :common:data android-library
class :common:ui android-library
class :design android-library
class :feature:account android-library
class :app android-application

```
# About

CATS - 2025

- Case study can be found in the repository: [docs/test_mobile_CA.pdf](test_mobile_CA.pdf)
- Project planning can be found too: [docs/project_planning.png](project_planning.png)