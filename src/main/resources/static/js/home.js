function onRemove(id) {
    if(confirm("Did You Really Want to Remove?")){
        location.href = `/remove-book?id=${id}`;
    }
}