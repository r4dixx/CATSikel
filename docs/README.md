# Module Graph

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  subgraph :core
    :core:ui["ui"]
    :core:utils["utils"]
  end
  subgraph :data
    :data:local["local"]
    :data:api["api"]
    :data:repository["repository"]
  end
  subgraph :feature
    :feature:banks["banks"]
    :feature:account["account"]
  end
  :design --> :core:ui
  :feature:banks --> :core:ui
  :feature:banks --> :core:utils
  :feature:banks --> :design
  :feature:banks --> :domain
  :feature:account --> :core:ui
  :feature:account --> :core:utils
  :feature:account --> :design
  :feature:account --> :domain
  :app --> :core:ui
  :app --> :core:utils
  :app --> :data:local
  :app --> :data:api
  :app --> :data:repository
  :app --> :design
  :app --> :domain
  :app --> :feature:account
  :app --> :feature:banks
  :data:repository --> :data:local
  :data:repository --> :data:api
  :data:repository --> :domain

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
class :design android-library
class :core:ui android-library
class :feature:banks android-library
class :core:utils android-library
class :domain android-library
class :feature:account android-library
class :app android-application
class :data:local android-library
class :data:api android-library
class :data:repository android-library

```
# About

CATS - 2025

- Case study can be found in the repository: [docs/test_mobile_CA.pdf](test_mobile_CA.pdf)
- Project planning can be found too: [docs/project_planning.png](project_planning.png)