# RouletteItem 
#### 가상창고를 생성하고 관리할 수 있는 플러그인 입니다.
![2022-07-08_13 59 24](https://user-images.githubusercontent.com/61282478/177920299-124e252a-e93f-405c-90f4-06073dc2d9ad.png)

· 기본적인 명령어는 다음과 같습니다.
<br/>  /창고(virtualchest) : 창고 명령어를 확인 또는 창고를 오픈합니다. (오피의 경우 명령어 확인, 오피가 없는경우 창고 오픈)
<br/>  /창고 저장 : 창고 데이터를 저장합니다.
<br/>  /창고 리로드 : 창고 데이터를 리로드합니다.
<br/>  /창고 활성화/비활성화 : 활성화 여부를 변경할 수 있습니다.
<br/>  /창고 크기조절 <닉네임> <창고번호> <1-6> : 해당 플레이어의 창고의 크기를 조절 할 수 있습니다. 크기를 줄이는경우 아이템이 유실될 수 있습니다.
<br/>  /창고 삭제 <닉네임> <창고번호> : 해당 창고를 제거합니다.
<br/>  /창고 추가 <닉네임> : 해당 플레이어에게 창고를 1개 추가합니다.
<br/>  /창고 열기 <닉네임>: op가 있는경우 창고를 오픈할 수 있는 명령어입니다.


· 기본적으로 데이터가 저장되는곳은 플러그인 폴더내 VirtualChest/data.yml입니다. 
<br/>  API접근을 통해 저장방식을 변경할 수 있습니다.



· API
<br/>  · class VirtualChestAPI
<br/>    static DataManager getManager() : 창고 관리 클래스를 반환합니다. 

<br/>  · class DataManager
<br/>    void setDataAccessObject(ChestDao) : 창고 데이터를 불러오기 위해 사용하는 dao 객체를 지정합니다. 
<br/>    플러그인 활성화 후 3초뒤에 설정된 dao객체로부터 데이터를 불러옵니다.

<br/>  · interface ChestDao
<br/>    Map<UUID, List<Inventory>> load() : 데이터를 불러오는 메소드입니다. UUID에 플레이어, List<Inventory>에는 가상창고 1번부터 해당되는 데이터들이 있습니다.
<br/>    void save(Map<UUID, List<Inventory>) : 데이터를 저장하는 메소드입니다.

기본적인 구현 예시는 ConfigDao 객체를 참조하시면 되겠습니다.
