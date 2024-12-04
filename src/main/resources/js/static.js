document.getElementById('searchForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('searchInput').value;
    fetch(`/search?name=${name}`)
        .then(response => response.json())
        .then(data => {
            const taskList = document.getElementById('taskList');
            taskList.innerHTML = '';
            data.forEach(task => {
                const taskItem = document.createElement('div');
                taskItem.textContent = task.name;
                taskList.appendChild(taskItem);
            });
        });
});